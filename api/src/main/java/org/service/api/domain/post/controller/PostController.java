package org.service.api.domain.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.post.controller.business.PostBusiness;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.service.PostService;
import org.service.api.domain.user.model.User;
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
    private final PostBusiness postBusiness;

    // 게시글 등록
    @PostMapping("")
    public PostDto register(
            @ModelAttribute PostRequest request,
            @UserSession User user
            ) throws IOException {
        request.getFiles().forEach(file -> log.info("file = {}", file.getOriginalFilename()));

        return postService.register(request, user);
    }

    @PostMapping("/")

    // 전체 게시글 가져오기
    @GetMapping("/get-list")
    public List<PostDto> getList(
            @UserSession User user
    ) {
        return postService.getList(user);
    }

    // 유저별 게시글 가져오기
    @GetMapping("/get-list/user")
    public List<PostDto> getListOfUser(
            @UserSession User user
    ) {
        return postBusiness.getPostListWithUserId(user);
    }


}
