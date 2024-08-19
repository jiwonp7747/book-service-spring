package org.service.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.service.api.domain.token.helper.TokenHelperIfs;
import org.service.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelper;

    public TokenDto issueAccessToken(Long userId) {
        var data=new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueAcessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var data=new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueAcessToken(data);
    }
}
