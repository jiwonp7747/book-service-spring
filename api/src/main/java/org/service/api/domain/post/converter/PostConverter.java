package org.service.api.domain.post.converter;

import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.db.post.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class PostConverter { // 데이터 변환

    public PostDto toDto(PostEntity postEntity) {

        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postType(postEntity.getPostType())
                .status(postEntity.getStatus())
                .postedAt(postEntity.getPostedAt())
                .userId(postEntity.getUserId())
                .build()
                ;
    }

    public PostEntity toEntity(PostRequest request) {
        return PostEntity.builder()
                .postType(request.getPostType())
                .title(request.getTitle())
                .content(request.getContent())
                .userId(request.getUserId())
                .build()
                ;
    }

}
