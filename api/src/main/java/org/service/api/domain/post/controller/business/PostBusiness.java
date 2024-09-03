package org.service.api.domain.post.controller.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.converter.PostConverter;
import org.service.api.domain.post.service.PostService;
import org.service.api.domain.user.model.User;
import org.service.db.image.ImageEntity;
import org.service.db.image.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostBusiness {

    @Value("${image.upload.dir}")
    private String uploadDir;
    @Value("${image.access.url}")
    private String accessUrl;

    private final PostService postService;
    private final PostConverter postConverter;
    private final ImageRepository imageRepository;

    public List<PostDto> getPostListWithUserId(User user) {
        var entityList=postService.getPostListWithUserId(user.getId());

        return entityList.stream().map(it->{
            var dto=postConverter.toDto(it);
            var imageEntity=imageRepository.findFirstByPostIdOrderByIdDesc(it.getId())
                    .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
            dto.setImageUrl(imageEntity.getUrl());
            return dto;
        })
                .collect(Collectors.toList());
    }

}
