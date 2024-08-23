package org.service.api.domain.post.controller.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.post.controller.model.PostDto;
import org.service.api.domain.post.controller.model.PostRequest;
import org.service.api.domain.post.converter.PostConverter;
import org.service.api.domain.post.service.PostService;
import org.service.db.image.ImageEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

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

    public PostDto register(PostRequest request) {
        var postEntity =postConverter.toEntity(request);

        /*//실제 경로 가져오기
        File staticImagesDir=new ClassPathResource("static/images").getFile();
        String realPath=staticImagesDir.getAbsolutePath();
        //String realPath="/Users/jiwonp/patamon/book_service_spring/api/src/main/resources/static/images";
        log.info("실제경로 입니다아아앙: {}", realPath);

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

            var imageEntity= ImageEntity.builder()
                    .postId(newEntity.getId())
                    .url(fileUrl)
                    .path(filePath)
                    .build();
            imageRepository.save(imageEntity);
        }*/

        return null;
    }
}
