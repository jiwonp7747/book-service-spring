package org.service.api.domain.user.converter;

import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.db.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserEntity toEntity(UserRequest request) {

        return UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .nickname(request.getNickname())
                .build();
    }

    public UserDto toDto(UserEntity entity) {

        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .nickName(entity.getNickname())
                .loginAt(entity.getLoginAt())
                .registeredAt(entity.getRegisteredAt())
                .unregisteredAt(entity.getUnregisteredAt())
                .status(entity.getStatus())
                .build();
    }
}
