package cn.eliadoarias.springdemo.handler;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.util.ExceptionUtil;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(1000)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BasicResult<Object> handleGlobalException(Exception e) {
        ExceptionUtil.printError(e);
        return BasicResult.error(ExceptionEnum.SERVER_ERROR);
    }
}
