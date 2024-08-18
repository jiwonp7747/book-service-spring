package org.service.api.domain.post.controller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.enums.PostStatus;
import org.service.db.post.enums.PostType;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto { // 응답 주는 용도

    private Long id;

    private PostType postType;

    private String title;

    private String content;

    private PostStatus status;

    private LocalDateTime postedAt;

    private Long userId;

    private int price;

    private String imageUrl;
}
