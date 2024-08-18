package org.service.api.domain.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.service.PostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PostMapping("/register")
    public PostDto register(
            @ModelAttribute PostRequest request
            ) throws IOException {
        request.getFiles().forEach(file -> log.info("file = {}", file.getOriginalFilename()));

        return postService.register(request);
    }

    // 게시글 가져오기
    @GetMapping("/get-list")
    public List<PostDto> getList(

    ) {
        return postService.getList();
    }
}
