package org.service.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserLoginRequest;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;




}
