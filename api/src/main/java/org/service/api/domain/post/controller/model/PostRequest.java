package org.service.api.domain.post.controller.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.enums.PostType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest { // 요청 받는 용도

    @NotNull
    private PostType postType;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private int price;

    @NotNull
    private List<MultipartFile> files;
}
