package org.service.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.service.api.domain.user.controller.UserController;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.converter.UserConverter;
import org.service.db.user.UserRepository;
import org.service.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDto register(UserRequest request) {
        var entity=userConverter.toEntity(request);

        return Optional.ofNullable(entity)
                .map(it->{
                    it.setStatus(UserStatus.REGISTERED);
                    it.setRegisteredAt(LocalDateTime.now());
                    var newEntity=userRepository.save(entity);
                    return userConverter.toDto(newEntity);
                })
                .orElseThrow(NullPointerException::new);
    }

    public UserDto me(Long id) {
        var entity=userRepository.findFirstByIdAndStatusOrderByIdDesc(id, UserStatus.REGISTERED)
                .orElseThrow(NullPointerException::new);
        return userConverter.toDto(entity);
    }

    public UserDto unregister(UserRequest request) {
        return null;
    }


}
