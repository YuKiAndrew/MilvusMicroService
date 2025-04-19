package com.ai.boost.controller;

import com.ai.boost.helper.common.Response;
import com.ai.boost.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/boost/insert/demo")
public class InsertController {

    @Autowired
    private InsertService insertService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insertDemoIntoMilvus() {
        int code = insertService.insertDemoFromSpecificDirectory();
        Response r = new Response();
        return r.success(code);
    }

}
