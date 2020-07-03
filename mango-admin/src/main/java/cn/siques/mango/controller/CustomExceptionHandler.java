package cn.siques.mango.controller;

import cn.siques.mangocore.exception.CusException;
import cn.siques.mangocore.http.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {
//    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){


            return JsonData.buildError(e.getMessage());

    }
}
