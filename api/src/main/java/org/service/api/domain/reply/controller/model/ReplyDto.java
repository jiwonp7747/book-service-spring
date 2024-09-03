package org.service.api.domain.reply.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.db.reply.enums.ReplyStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {

    private Long id;

    private String content;

    private ReplyStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long userId;

    private Long postId;

    private Long selectedPostId;
}
