package com.ai.boost.service;

import com.ai.boost.model.CarModel;
import com.ai.boost.model.req.SearchRequest;

import java.util.List;

public interface SearchService {

    List<CarModel> searchServicesByVector(SearchRequest searchReq);

    List<CarModel> searchServicesByVectorSec(SearchRequest searchRequest);
}
