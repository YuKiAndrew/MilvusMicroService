package com.ai.boost.config;

import com.ai.boost.helper.common.GlobalException;
import com.ai.boost.helper.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerCollection {
    @ExceptionHandler(GlobalException.class)
    public Response error(GlobalException e) {
        log.error("catch the global exception: {}", e.getMessage());
        Response r = new Response();
        return r.success();
    }

    @ExceptionHandler(RuntimeException.class)
    public Response error(RuntimeException e) {
        log.error("catch the run time exception: {}", e.getMessage(), e);
        Response r = new Response();
        return r.generalFail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response error(Exception e) {
        log.error("catch the general exception: {}", e.getMessage(), e);
        Response r = new Response();
        return r.generalFail();
    }

}
