package com.ai.boost.service.impl;

import com.ai.boost.helper.common.FluentMap;
import com.ai.boost.helper.common.GlobalParameter;
import com.ai.boost.service.InsertService;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.DescribeCollectionResponse;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.DescribeCollectionParam;
import io.milvus.param.collection.DropCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.highlevel.collection.CreateSimpleCollectionParam;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.DescribeCollectionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
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
        return 1;
    }
}
