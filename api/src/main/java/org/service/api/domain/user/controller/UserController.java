package org.service.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.token.controller.model.TokenResponse;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserLoginRequest;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.model.User;
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


    @GetMapping("/get-user")
    public UserDto getUser(
            @RequestParam Long id
    ) {
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", "application/json; charset=UTF-8");
        return userService.getUser(id);
        //return new ResponseEntity<>(userService.me(id), headers, HttpStatus.OK);
    }

    @GetMapping("")
    public UserDto me(
            @UserSession User user
    ) {
        return userService.me(user);
    }
}
