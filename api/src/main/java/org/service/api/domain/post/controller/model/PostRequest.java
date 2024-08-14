package org.service.api.domain.post.controller.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.enums.PostType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest { // 요청 받는 용도

    private PostType postType;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //TODO 세션 처리 기능 생기면 제거 why? 클라이언트에서 user id 를 아는 것이 말이 안됨.
    private Long userId;
}
