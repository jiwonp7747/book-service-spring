package org.service.api.domain.reply.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyRequest {

    @NotNull
    private Long postId;

    @NotBlank
    private String title;

    @NotNull
    private Long selectedPostId;
}
