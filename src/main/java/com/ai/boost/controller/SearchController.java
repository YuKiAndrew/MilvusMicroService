package com.ai.boost.controller;

import com.ai.boost.helper.common.Response;
import com.ai.boost.model.CarModel;
import com.ai.boost.model.req.SearchRequest;
import com.ai.boost.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai/boost/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/topkQuery", method = RequestMethod.POST)
    @ResponseBody
    public Response obtain(@RequestBody SearchRequest searchRequest) {
        List<CarModel> carModels = searchService.searchServicesByVector(searchRequest);
        return new Response<>().success(carModels);
    }

    @RequestMapping(value = "/topkQueryWithV2", method = RequestMethod.POST)
    @ResponseBody
    public Response obtainFromV2(@RequestBody SearchRequest searchRequest) {
        List<CarModel> carModels = searchService.searchServicesByVectorSec(searchRequest);
        return new Response<>().success(carModels);
    }

}
