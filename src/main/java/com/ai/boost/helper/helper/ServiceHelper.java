package com.ai.boost.helper.helper;

import com.ai.boost.model.CarModel;
import com.ai.boost.model.Resp.BulkResp;
import io.milvus.grpc.SearchResults;
import io.milvus.response.SearchResultsWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
