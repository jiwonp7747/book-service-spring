package org.service.api.domain.reply.converter;

import org.service.api.domain.reply.controller.model.ReplyDto;
import org.service.api.domain.reply.controller.model.ReplyRequest;
import org.service.api.domain.user.model.User;
import org.service.db.post.PostEntity;
import org.service.db.reply.ReplyEntity;
import org.service.db.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class ReplyConverter {

    public ReplyEntity toEntity(UserEntity user, PostEntity post, PostEntity selectedPost, String content) {
        return ReplyEntity.builder()
                .user(user)
                .post(post)
                .content(content)
                .selectedPostId(selectedPost.getId())
                .build()
                ;
    }

    public ReplyDto toDto(ReplyEntity replyEntity) {
        return ReplyDto.builder()
                .userId(replyEntity.getUser().getId())
                .postId(replyEntity.getPost().getId())
                .selectedPostId(replyEntity.getSelectedPostId())
                .id(replyEntity.getId())
                .registeredAt(replyEntity.getRegisteredAt())
                .unregisteredAt(replyEntity.getUnregisteredAt())
                .content(replyEntity.getContent())
                .status(replyEntity.getStatus())
                .build()
                ;
    }
}
