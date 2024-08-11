package org.service.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.service.api.domain.user.controller.UserController;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.converter.UserConverter;
import org.service.db.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDto register(UserRequest request) {

        // TODO status set 필요
        var entity=userConverter.toEntity(request);

        return null;
    }
}
