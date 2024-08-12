package org.service.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.error.ErrorCodeIfs;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공적 결과")
                .build()
                ;
    }

    public static Result Error(ErrorCodeIfs errorCode) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription("에러 발생")
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(description)
                .build();
    }
}
