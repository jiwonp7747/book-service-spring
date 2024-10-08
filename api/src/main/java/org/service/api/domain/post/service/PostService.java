package org.service.api.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.converter.PostConverter;
import org.service.api.domain.user.converter.UserConverter;
import org.service.api.domain.user.model.User;
import org.service.api.domain.user.service.UserService;
import org.service.db.image.ImageEntity;
import org.service.db.image.ImageRepository;
import org.service.db.post.PostEntity;
import org.service.db.post.PostRepository;
import org.service.db.post.enums.PostStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService { // 비즈니스 로직 처리

    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final ImageRepository imageRepository;
    private final UserConverter userConverter;
    private final UserService userService;

    @Value("${image.upload.dir}")
    private String uploadDir;
    @Value("${image.access.url}")
    private String accessUrl;

    public PostDto register(PostRequest request, User user) throws IOException {
        var userEntity=userService.getUserWithThrow(user.getId());
        var postEntity =postConverter.toEntity(request, userEntity);
        postEntity.setPostedAt(LocalDateTime.now());
        postEntity.setStatus(PostStatus.REGISTERED);

        //실제 경로 가져오기
        File staticImagesDir=new ClassPathResource("static/images").getFile();
        String realPath=staticImagesDir.getAbsolutePath();
        log.info("실제경로 입니다아아앙: {}", realPath);

        var newEntity=postRepository.save(postEntity);
        log.info("new Entity: {}", newEntity);

//        List<ImageEntity> images=new ArrayList<>();
        for (MultipartFile file : request.getFiles()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = realPath + File.separator + fileName;
            String fileUrl = accessUrl + fileName;

            // logging
            System.out.println(filePath);

            File dest = new File(filePath);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

           var imageEntity=ImageEntity.builder()
                   .post(newEntity)
                   .url(fileUrl)
                   .path(filePath)
                   .build();
           imageRepository.save(imageEntity);
        }

        return postConverter.toDto(newEntity);
    }

    public PostDto update(Long id, PostRequest request, User user) throws IOException {

        return null;
    }


    public List<PostDto> getList(User user) {
        var entityList= postRepository.findAll();

        return entityList.stream().map(it->{
            var dto=postConverter.toDto(it);
            var postId=it.getId();

            var imageEntity=imageRepository.findFirstByPostIdOrderByIdDesc(postId)
                    .orElseThrow(()->new ApiException(ErrorCode.BAD_REQUEST));

            //var imageEntityList=imageRepository.findAllByPostIdOrderByIdDesc(postId);

            dto.setImageUrl(imageEntity.getUrl());
            return dto;
        }).collect(Collectors.toList());
    }

    public Page<PostDto> getListByPage(User user, int page, int size) {

        // Pageable 인스턴스 생성
        Pageable pageable = PageRequest.of(page, size);

        // Page 가져오기
        var entityPage=postRepository.findByStatusOrderByIdDesc(PostStatus.REGISTERED, pageable);

        // 엔티티 리스트를 DTO 리스트로 변환
        List<PostDto> dtoList= entityPage.stream().map(it->{
            var dto=postConverter.toDto(it);
            var postId=it.getId();

            var imageEntity=imageRepository.findFirstByPostIdOrderByIdDesc(postId)
                    .orElseThrow(()->new ApiException(ErrorCode.BAD_REQUEST));

            //var imageEntityList=imageRepository.findAllByPostIdOrderByIdDesc(postId);

            dto.setImageUrl(imageEntity.getUrl());
            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public PostEntity getPostWithThrow(Long id) {
        return postRepository.findById(id).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<PostEntity> getPostListWithUserId(Long userId) {
        return postRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, PostStatus.REGISTERED);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
