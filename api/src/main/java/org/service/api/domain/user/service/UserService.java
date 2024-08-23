package org.service.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.error.UserErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.token.business.TokenBusiness;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.user.controller.UserController;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserLoginRequest;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.converter.UserConverter;
import org.service.api.domain.user.model.User;
import org.service.db.user.UserEntity;
import org.service.db.user.UserRepository;
import org.service.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    public UserDto register(UserRequest request) {
        var entity=userConverter.toEntity(request);

        return Optional.ofNullable(entity)
                .map(it->{
                    it.setStatus(UserStatus.REGISTERED);
                    it.setRegisteredAt(LocalDateTime.now());
                    var newEntity=userRepository.save(entity);
                    return userConverter.toDto(newEntity);
                })
                .orElseThrow(()->new ApiException(ErrorCode.BAD_REQUEST));
    }

    public UserDto getUser(Long id) { //테스트용 유저 가져오기
        var entity=userRepository.findFirstByIdAndStatusOrderByIdDesc(id, UserStatus.REGISTERED)
                .orElseThrow(()->new ApiException(ErrorCode.BAD_REQUEST));

        return userConverter.toDto(entity);
    }

    public UserDto me(User user) {
        log.info("왜 값이 없다고하는거지?? {}", user.getEmail());
        var entity=userRepository.findFirstByEmailAndStatusOrderByIdDesc(user.getEmail(), UserStatus.REGISTERED).get(); //여긴 null일수가 없음
        log.info("me: {}", entity.getId());

        return userConverter.toDto(entity);
    }


    public UserEntity getUserWithThrow(Long userId) {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
                .orElseThrow(()->new ApiException(UserErrorCode.USER_NOT_FOUNT));
    }

    public UserDto unregister(Long id) {
        return null;
    }


    public TokenResponse login(UserLoginRequest request) {
        var entity=userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(request.getEmail(), request.getPassword(), UserStatus.REGISTERED)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
        var tokenResponse=tokenBusiness.issueToken(entity);

        return tokenResponse;
    }
}
