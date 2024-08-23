package org.service.api.common.exception;

import lombok.Getter;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.error.ErrorCodeIfs;
import org.service.api.common.error.TokenErrorCode;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiException extends RuntimeException {

    private final ErrorCodeIfs errorCode;
    private final String errorDescription;

    private HttpStatusCode errCode;

    public ApiException(ErrorCodeIfs errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorDescription=errorCode.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIfs errorCode, Throwable tx) {
        super(tx);
        this.errorCode = errorCode;
        this.errorDescription=errorCode.getDescription();
    }
}
