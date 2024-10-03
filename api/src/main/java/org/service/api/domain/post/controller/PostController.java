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
import org.springframework.data.domain.Page;
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
        // 파일 잘 들어오는 지 로그
        request.getFiles().forEach(file -> log.info("file = {}", file.getOriginalFilename()));

        return postService.register(request, user);
    }

    // 게시글 수정
    @PatchMapping("/{id}")
    public PostDto update(
            @PathVariable("id") Long id,
            @ModelAttribute PostRequest request,
            @UserSession User user
    ) throws IOException {
        return postService.update(id, request, user);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Long id,
            @UserSession User user
    ) {
        postBusiness.delete(id, user);
    }
    // 전체 게시글 가져오기
    @GetMapping("/get-list/all")
    public List<PostDto> getList(
            @UserSession User user
    ) {
        return postService.getList(user);
    }

    // 정해진 갯수만크 게시글 가져오기
    @GetMapping("/get-list/page")
    public Page<PostDto> getListByPage(
            @UserSession User user,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        log.info("Page 쿼리 확인 !!!!!: page = {}, size = {}", page, size);
        return postService.getListByPage(user, page, size);
    }


    // 유저별 게시글 가져오기
    @GetMapping("/get-list/user")
    public List<PostDto> getListOfUser(
            @UserSession User user
    ) {
        return postBusiness.getPostListWithUserId(user);
    }


}
