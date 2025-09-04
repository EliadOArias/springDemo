package cn.eliadoarias.springdemo.util;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.result.BasicResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtil {
    public static void printError(Exception e){
        log.error("Catch an error", e);
    }
}
