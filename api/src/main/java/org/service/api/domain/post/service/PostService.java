package org.service.api.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.converter.PostConverter;
import org.service.db.post.PostRepository;
import org.service.db.post.enums.PostStatus;
import org.service.db.post.enums.PostType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService { // 비즈니스 로직 처리

    private final PostRepository postRepository;
    private final PostConverter postConverter;

    public PostDto register(PostRequest request) {
        var entity=postConverter.toEntity(request);
        entity.setPostedAt(LocalDateTime.now());
        entity.setStatus(PostStatus.REGISTERED);
        entity.setPostType(request.getPostType());

        var newEntity=postRepository.save(entity);
        log.info("new entity: {}", newEntity);

        return postConverter.toDto(newEntity);
    }

    public List<PostDto> getList() {
        var entityList= postRepository.findAll();

        return entityList.stream().map(it->{
            return postConverter.toDto(it);
        }).collect(Collectors.toList());
    }
}
