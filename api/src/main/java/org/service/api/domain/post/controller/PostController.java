package org.service.api.domain.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 게시글 가져오기
    @GetMapping("/get-list")
    public List<PostDto> getList(

    ) {
        return postService.getList();
    }
}
