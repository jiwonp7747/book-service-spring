package org.service.api.common.api;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCodeIfs;

@Slf4j
@Getter
public class Api<T>{

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data) {
        var api = new Api<T>();
        api.result=Result.OK();
        api.body=data;

        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCode) {
        var api = new Api<Object>();
        api.result=Result.Error(errorCode);

        log.info("Api 의 ERROR 함수입니다 {}", api);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){
        var api=new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs, description);
        return api;
    }
}
