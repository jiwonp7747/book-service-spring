package org.service.api.domain.user;

import org.junit.jupiter.api.Test;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.service.UserService;

public class UserTest {

    @Test
    void 회원가입() {
        //given
        UserRequest request=UserRequest.builder()
                .email("abc@gmail.com")
                .password("12345678a")
                .nickname("홍길동")
                .address("서울특별시 강동구")
                .build()
                ;

        //when

        //then
    }
}
