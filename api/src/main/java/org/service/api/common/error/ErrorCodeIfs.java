package org.service.api.common.error;

import org.springframework.http.HttpStatus;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();
}
