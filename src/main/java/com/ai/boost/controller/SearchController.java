package com.ai.boost.controller;

import com.ai.boost.helper.common.Response;
import com.ai.boost.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/boost/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/obtain")
    public Response obtain() {
        return new Response<>();
    }

}
