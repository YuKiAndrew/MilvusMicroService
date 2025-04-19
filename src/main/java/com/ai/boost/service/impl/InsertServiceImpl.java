package com.ai.boost.service.impl;

import com.ai.boost.helper.common.FluentMap;
import com.ai.boost.helper.common.GlobalParameter;
import com.ai.boost.service.InsertService;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.DescribeCollectionResponse;
import io.milvus.grpc.FlushResponse;
import io.milvus.grpc.MutationResult;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.*;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.index.CreateIndexParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InsertServiceImpl implements InsertService {

    @Autowired
    private MilvusServiceClient milvusServiceClient;

    @Override
    public int insertDemoFromSpecificDirectory() {
        R<DescribeCollectionResponse> responseR = milvusServiceClient.describeCollection(
                DescribeCollectionParam.
                        newBuilder().withCollectionName(GlobalParameter.COLLECTION_NAME).build());
        if (responseR.getData() != null) {
            milvusServiceClient.dropCollection(DropCollectionParam.newBuilder().withCollectionName(GlobalParameter.COLLECTION_NAME).build());
        }
        int dimension = 64;
        FieldType idType = FieldType.newBuilder()
                        .withName("car_id")
                        .withDataType(DataType.Int64)
                        .withPrimaryKey(true)
                        .withAutoID(false)
                        .build();
        FieldType carModel = FieldType.newBuilder()
                .withName("car_model")
                .withDataType(DataType.VarChar)
                .withTypeParams(new FluentMap<String,String>().chainPut("max_length","100")).build();
        FieldType carMake = FieldType.newBuilder()
                .withName("car_make")
                .withDataType(DataType.VarChar)
                .withTypeParams(new FluentMap<String,String>().chainPut("max_length","100")).build();
        FieldType carV = FieldType.newBuilder()
                .withName("car_vector")
                .withDataType(DataType.FloatVector)
                .withDimension(dimension)
                .build();

        CreateCollectionParam ccp = CreateCollectionParam
                .newBuilder()
                .withCollectionName(GlobalParameter.COLLECTION_NAME)
                .withShardsNum(2)
                .addFieldType(idType)
                .addFieldType(carModel)
                .addFieldType(carMake)
                .addFieldType(carV)
                .build();
        System.out.println("Creating example collection: " + GlobalParameter.COLLECTION_NAME);
        System.out.println("Schema: " + ccp);

        // temporarily set metric as IP
        milvusServiceClient.createCollection(ccp);
        log.info("Database has been established");

        Random random = new Random();
        int singleNum = 1000;
        int insertRounds = 2;
        long insertTotalTime = 0L;
        for (int r =0; r < insertRounds; r++) {
            List<Long> carIdArr = new ArrayList<>();
            List<String> carMakeArr = new ArrayList<>();
            List<String> carModelArr = new ArrayList<>();
            List<List<Float>> carInfoArr = new ArrayList<>();
            for (long i = r * singleNum; i < (r + 1) * singleNum; ++i) {
                carIdArr.add(i);
                int index = random.nextInt(GlobalParameter.CAR_MAKER.length);
                carMakeArr.add(GlobalParameter.CAR_MAKER[index]);
                List<String> strings = GlobalParameter.CAR_MODEL.get(GlobalParameter.CAR_MAKER[index]);
                if (strings == null) {
                    carModelArr.add("null");
                } else {
                    int moIndex = random.nextInt(strings.size());
                    carModelArr.add(strings.get(moIndex));
                }

                List<Float> vector = new ArrayList<>();
                for (int k = 0; k < dimension; ++k) {
                    vector.add(random.nextFloat());
                }
                carInfoArr.add(vector);
            }
            List<InsertParam.Field> fields = new ArrayList<>();
            fields.add(new InsertParam.Field(idType.getName(), carIdArr));
            fields.add(new InsertParam.Field(carMake.getName(),carMakeArr));
            fields.add(new InsertParam.Field(carModel.getName(), carModelArr));
            fields.add(new InsertParam.Field(carV.getName(), carInfoArr));
            InsertParam insertParam = InsertParam.newBuilder()
                    .withCollectionName(GlobalParameter.COLLECTION_NAME)
                    .withFields(fields)
                    .build();
            long startTime = System.currentTimeMillis();
            R<MutationResult> insertR = milvusServiceClient.insert(insertParam);
            if (!(insertR.getStatus() == 0)) {
                throw new RuntimeException(insertR.getMessage());
            }
            long endTime = System.currentTimeMillis();
            insertTotalTime += (endTime - startTime) / 1000;
        }
        log.info("Records has been inserted " + insertTotalTime);

        R<FlushResponse> flush = milvusServiceClient.flush(FlushParam.newBuilder()
                .withCollectionNames(Collections.singletonList(GlobalParameter.COLLECTION_NAME))
                .withSyncFlush(true)
                .withSyncFlushWaitingInterval(50L)
                .withSyncFlushWaitingTimeout(30L)
                .build());
        if (!(flush.getStatus() == 0)) {
            throw new RuntimeException(flush.getMessage());
        }

        final IndexType INDEX_TYPE = IndexType.AUTOINDEX;
        R<RpcStatus> indexR = milvusServiceClient.createIndex(
                CreateIndexParam.newBuilder()
                        .withCollectionName(GlobalParameter.COLLECTION_NAME)
                        .withFieldName(carV.getName())
                        .withIndexType(INDEX_TYPE)
                        .withMetricType(MetricType.COSINE)
                        .withSyncMode(Boolean.TRUE)
                        .withSyncWaitingInterval(500L)
                        .withSyncWaitingTimeout(30L)
                        .build());

        if (!(indexR.getStatus() == 0)) {
            throw new RuntimeException(flush.getMessage());
        }

        R<RpcStatus> rpcStatusR = milvusServiceClient.loadCollection(LoadCollectionParam.newBuilder()
                .withCollectionName(GlobalParameter.COLLECTION_NAME)
                .withSyncLoad(true)
                .withSyncLoadWaitingInterval(500L)
                .withSyncLoadWaitingTimeout(100L)
                .build());

        if (!(rpcStatusR.getStatus() == 0)) {
            throw new RuntimeException(flush.getMessage());
        }
        return 1;
    }
}
