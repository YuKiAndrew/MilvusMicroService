package com.ai.boost.helper.helper;

import com.ai.boost.model.CarModel;
import io.milvus.grpc.SearchResults;
import io.milvus.response.SearchResultsWrapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceHelper {
    public void obtainCarMakeList(List<CarModel> list, SearchResults resp) {

        SearchResultsWrapper wrapperSearch = new SearchResultsWrapper(resp.getResults());
        List<Long> id = (List<Long>) wrapperSearch.getFieldData("car_id", 0);
        List<String> carModelList = (List<String>) wrapperSearch.getFieldData("car_model", 0);
        List<String> carMakerList = (List<String>) wrapperSearch.getFieldData("car_make", 0);
        List<List<Float>> carVectorList = (List<List<Float>>) wrapperSearch.getFieldData("car_vector", 0);
        for (int i = 0; i < id.size(); i++) {
            CarModel carModel = new CarModel();
            carModel.setCarModel(carModelList.get(i));
            carModel.setCarId(id.get(i));
            carModel.setCarMake(carMakerList.get(i));
            carModel.setCarVector(carVectorList.get(i));
            list.add(carModel);
        }
    }
}
