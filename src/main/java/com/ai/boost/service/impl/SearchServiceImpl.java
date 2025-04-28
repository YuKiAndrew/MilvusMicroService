package com.ai.boost.service.impl;

import com.ai.boost.helper.common.ArrayListStream;
import com.ai.boost.helper.common.GlobalParameter;
import com.ai.boost.helper.helper.ServiceHelper;
import com.ai.boost.model.CarModel;
import com.ai.boost.model.Resp.BulkResp;
import com.ai.boost.model.req.BulkSearchRequest;
import com.ai.boost.model.req.SearchRequest;
import com.ai.boost.service.SearchService;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.SearchResults;
import io.milvus.param.R;
import io.milvus.param.dml.SearchParam;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.request.data.FloatVec;
import io.milvus.v2.service.vector.response.SearchResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MilvusServiceClient milvusServiceClient;

    @Autowired
    private MilvusClientV2 milvusClientV2;

    @Autowired
    private ServiceHelper serviceHelper;

    @Override
    public List<CarModel> searchServicesByVector(SearchRequest searchReq) {

        SearchParam searchReqMilvus = SearchParam.newBuilder()
                .withCollectionName(GlobalParameter.COLLECTION_NAME)
                .withVectorFieldName("car_vector")
                .withFloatVectors( new ArrayListStream<>().addAll(searchReq.getSearchList()))
                .withTopK(3)
                .withOutFields(Arrays.asList("car_id", "car_model", "car_make", "car_vector"))
                .build();
        R<SearchResults> searchResp = milvusServiceClient.search(searchReqMilvus);
        List<CarModel> list = new ArrayList<>();

        if(searchResp.getStatus() == R.Status.Success.getCode()){
            //respSearch.getData().getStatus() == R.Status.Success
            SearchResults resp = searchResp.getData();
            if(!resp.hasResults()){ //判断是否查到结果
                return new ArrayList<>();
            }
            serviceHelper.obtainCarMakeList(list, resp);

        } else {
            throw new RuntimeException(searchResp.getMessage());
        }
        return list;
    }

    @Override
    public List<CarModel> searchServicesByVectorSec(SearchRequest searchRequest) {
        FloatVec floatVec = new FloatVec(searchRequest.getSearchList());
        SearchReq searchReq = SearchReq.builder().collectionName(GlobalParameter.COLLECTION_NAME).data(Collections.singletonList(floatVec)).topK(3).build();
        SearchResp search = milvusClientV2.search(searchReq);
        List<List<SearchResp.SearchResult>> searchResults = search.getSearchResults();
        List<CarModel> list = new ArrayList<>();
        for (List<SearchResp.SearchResult> results : searchResults) {

            for (SearchResp.SearchResult result : results) {
                CarModel cm = new CarModel();
                cm.setCarId((Long) result.getId());
                list.add(cm);
            }
        }
        return list;
    }

    @Override
    public List<BulkResp> searchServicesByBulk(BulkSearchRequest bulkSearchRequest) {
        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName(GlobalParameter.COLLECTION_NAME)
                .withVectorFieldName("car_vector")
                .withFloatVectors(bulkSearchRequest.getSearchList())
                .withTopK(3)
                .withOutFields(Arrays.asList("car_id", "car_model", "car_make", "car_vector"))
                .build();
        R<SearchResults> search = milvusServiceClient.search(searchParam);
        List<BulkResp> bulkResps = new ArrayList<>();
        if (search.getStatus() ==  R.Status.Success.getCode()) {
            SearchResults resp = search.getData();
            if(!resp.hasResults()){ //判断是否查到结果
                return new ArrayList<>();
            }
             bulkResps = serviceHelper.obtainBulkResp(resp);
        }
        return bulkResps;
    }
}
