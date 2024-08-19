package org.service.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        //null 체크
        Objects.requireNonNull(accessToken, ()-> {throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, ()-> {throw new ApiException(ErrorCode.NULL_POINT);});


        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
