package com.chunqiu.tensquare.user.controller;

import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *功能描述 
 * @author Wangchunqiu
 * @date $
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error (Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

}
