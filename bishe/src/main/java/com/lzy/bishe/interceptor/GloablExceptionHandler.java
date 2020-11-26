package com.lzy.bishe.interceptor;

import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lizhongyi
 */
@ControllerAdvice
public class GloablExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultDTO handleException(Exception e) {
        String msg = e.getMessage();
        return ResultDTO.errorOf(500,msg);
    }
}
