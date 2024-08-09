package org.service.api.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.db.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService { // 비즈니스 로직 처리

    private final PostRepository postRepository;

    public PostDto register(PostRequest request) {
        return null;
    }
}
