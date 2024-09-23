package org.service.api.domain.reply.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.enums.PostStatus;
import org.service.db.post.enums.PostType;
import org.service.db.reply.enums.ReplyStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {

    private Long id;

    private String content;

    private LocalDateTime registeredAt;

    private PostResponse post;

   /* public static class UserResponse {

    }*/

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostResponse {
        private Long id;

        private String title;

        private String content;

        private int price;

        private String imageUrl;
    }

    //TODO  교환 요청 시 나의 책 필요하면 추가
}
