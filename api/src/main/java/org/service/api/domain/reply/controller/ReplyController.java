package org.service.api.domain.reply.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.reply.business.ReplyBusiness;
import org.service.api.domain.reply.controller.model.ReplyDto;
import org.service.api.domain.reply.controller.model.ReplyRequest;
import org.service.api.domain.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyBusiness replyBusiness;

    // 교환 댓글 등록
    @PostMapping("/register")
    public ReplyDto register(
            @UserSession User user,
            @Valid
            @RequestBody ReplyRequest request
    ) {
        return replyBusiness.register(user, request);
    }

    // 게시글에 해당하는 교환 댓글 가져오기
    @GetMapping("/get-list")
    public List<ReplyDto> getList(
            @RequestParam Long postId
    ) {
        return replyBusiness.getList(postId);
    }

    // 유저가 쓴 댓글들 가져오기
}
