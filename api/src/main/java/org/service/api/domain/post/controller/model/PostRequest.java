package org.service.api.domain.post.controller.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostRequest { // 요청 받는 용도

    private Integer boardType;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 150, nullable = false)
    private String status;

    private LocalDateTime postedAt;

    //TODO 세션 처리 기능 생기면 제거 why? 클라이언트에서 user id 를 아는 것이 말이 안됨.
    //private Long userId;
}
