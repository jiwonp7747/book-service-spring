package org.service.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserLoginRequest;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public UserDto register(
            @Valid
            @RequestBody
            UserRequest request
    ) {
        return userService.register(request);
    }

    @GetMapping("")
    public UserDto getUser(
        @RequestParam Long id
    ) {
        return userService.me(id);
    }

    @PostMapping("/login") // 토큰 발행
    public TokenResponse login(
            @Valid
            @RequestBody UserLoginRequest request
    ) {
        return userService.login(request);
    }
}
