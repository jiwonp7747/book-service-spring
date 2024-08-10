package org.service.api.domain.user.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String nickName;

    private String address;

    private String status;

    private LocalDateTime loginAt;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
