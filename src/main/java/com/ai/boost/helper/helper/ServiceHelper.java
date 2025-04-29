package com.ai.boost.helper.helper;

import com.ai.boost.model.CarModel;
import com.ai.boost.model.Resp.BulkResp;
import com.google.protobuf.Descriptors;
import io.milvus.grpc.FieldData;
import io.milvus.grpc.QueryResults;
import io.milvus.grpc.SearchResults;
import io.milvus.response.QueryResultsWrapper;
import io.milvus.response.SearchResultsWrapper;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ServiceHelper {
    @SuppressWarnings("unchecked")
    public void obtainCarMakeList(List<CarModel> list, SearchResults resp) {

        SearchResultsWrapper wrapperSearch = new SearchResultsWrapper(resp.getResults());
        List<Long> id = (List<Long>) wrapperSearch.getFieldData("car_id",0);
        List<String> carModelList = (List<String>) wrapperSearch.getFieldData("car_model", 0);
        List<String> carMakerList = (List<String>) wrapperSearch.getFieldData("car_make", 0);
        List<List<Float>> carVectorList = (List<List<Float>>) wrapperSearch.getFieldData("car_vector",0);
        for (int i = 0; i < id.size(); i++) {
            CarModel carModel = new CarModel();
            carModel.setCarModel(carModelList.get(i));
            carModel.setCarId(id.get(i));
            carModel.setCarMake(carMakerList.get(i));
            carModel.setCarVector(carVectorList.get(i));
            list.add(carModel);
        }
    }

    public List<BulkResp> obtainBulkResp(SearchResults resp) {

        SearchResultsWrapper wrapperSearch = new SearchResultsWrapper(resp.getResults());
        List<BulkResp> result = new ArrayList<>();

        for (int j = 0; j < resp.getResults().getNumQueries(); j++) {
            BulkResp bulkResp = new BulkResp();
            List<Long> id = (List<Long>) wrapperSearch.getFieldData("car_id", j);
            List<String> carModelList = (List<String>) wrapperSearch.getFieldData("car_model", j);
            List<String> carMakerList = (List<String>) wrapperSearch.getFieldData("car_make", j);
            List<List<Float>> carVectorList = (List<List<Float>>) wrapperSearch.getFieldData("car_vector", j);
            bulkResp.setIndex(j);
            List<CarModel> list = new ArrayList<>();
            for (int i = 0; i < id.size(); i++) {
                CarModel carModel = new CarModel();
                carModel.setCarModel(carModelList.get(i));
                carModel.setCarId(id.get(i));
                carModel.setCarMake(carMakerList.get(i));
                carModel.setCarVector(carVectorList.get(i));
                list.add(carModel);
            }
            bulkResp.setCarModelList(list);
            result.add(bulkResp);
        }
        return result;
    }

    public List<CarModel> obtainResult(QueryResults data) {
        List<CarModel> list = new ArrayList<>();
        QueryResultsWrapper qrw = new QueryResultsWrapper(data);
        List<Long> carId = (List<Long>) qrw.getFieldWrapper("car_id").getFieldData();
        List<String> carModel = (List<String>) qrw.getFieldWrapper("car_model").getFieldData();
        List<String> carMake = (List<String>) qrw.getFieldWrapper("car_make").getFieldData();
        List<List<Float>> carVector = (List<List<Float>>) qrw.getFieldWrapper("car_vector").getFieldData();
        for(int i = 0; i < carId.size(); i++) {
            CarModel cm = new CarModel();
            cm.setCarId(carId.get(0));
            cm.setCarMake(carMake.get(0));
            cm.setCarModel(carModel.get(0));
            cm.setCarVector(carVector.get(0));
            list.add(cm);
        }
        return list;
    }
}
