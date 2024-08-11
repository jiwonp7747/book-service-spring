package org.service.api.domain.user.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickName;

    @NotBlank
    private String address;
}
