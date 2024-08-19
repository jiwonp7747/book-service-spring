package org.service.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.token.converter.TokenConverter;
import org.service.api.domain.token.service.TokenService;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.db.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(UserEntity user) {
        return Optional.ofNullable(user)
                .map(ue ->{
                    return user.getId();
                })
                .map(userId->{
                    var accessTokenDto=tokenService.issueAccessToken(userId);
                    var refreshTokenDto=tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessTokenDto, refreshTokenDto);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.BAD_REQUEST));
    }
}
