package org.service.api.domain.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PostMapping("/register")
    public PostDto register(
            @Valid
            @RequestBody(required = true) PostRequest request
            ) {
        return postService.register(request);
    }
}
