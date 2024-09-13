package org.service.api.domain.post.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.service.PostService;
import org.service.api.domain.user.model.User;
import org.service.api.domain.user.service.UserService;
import org.service.db.post.PostEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostConverter { // 데이터 변환

    private final UserService userService;

    public PostDto toDto(PostEntity postEntity) {
        var userId= postEntity.getUserId();
        var nickname=userService.getUserWithThrow(userId).getNickname();

        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .status(postEntity.getStatus())
                .postedAt(postEntity.getPostedAt())
                .nickname(nickname)
                .price(postEntity.getPrice())
                .build()
                ;
    }

    public PostEntity toEntity(PostRequest request) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .build()
                ;
    }

    public PostEntity toEntity(PostRequest request, User user) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(user.getId())
                .price(request.getPrice())
                .build()
                ;
    }

}
