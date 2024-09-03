package org.service.api.domain.reply.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.post.service.PostService;
import org.service.api.domain.reply.controller.model.ReplyDto;
import org.service.api.domain.reply.controller.model.ReplyRequest;
import org.service.api.domain.reply.converter.ReplyConverter;
import org.service.api.domain.reply.service.ReplyService;
import org.service.api.domain.user.model.User;
import org.service.api.domain.user.service.UserService;
import org.service.db.post.PostRepository;
import org.service.db.reply.ReplyRepository;
import org.service.db.reply.enums.ReplyStatus;
import org.service.db.user.UserRepository;
import org.service.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyBusiness {

    private final ReplyService replyService;
    private final ReplyConverter replyConverter;
    private final PostService postService;
    private final UserService userService;
    private final ReplyRepository replyRepository;

    public ReplyDto register(User user, ReplyRequest request) {

        // 유저 entity 가져오기
        var userId=user.getId();
        var userEntity=userService.getUserWithThrow(userId);

        // post entity 가져오기
        var postId= request.getPostId();
        var postEntity=postService.getPostWithThrow(postId);

        // selected entity 가져오기
        var selectedPostId=request.getSelectedPostId();
        var selectedPostEntity=postService.getPostWithThrow(selectedPostId);

        var replyEntity=replyConverter.toEntity(userEntity, postEntity, selectedPostEntity, request.getTitle());

        var newEntity=replyService.register(replyEntity);
        var replyDto=replyConverter.toDto(newEntity);

        return replyDto;
    }

    // 게시글에 해당하는 댓글들 가져오기
    public List<ReplyDto> getList(Long postId) {
        var postEntity=postService.getPostWithThrow(postId); // 유효한 id 인지 체크
        var replyEntityList=replyService.getList(postEntity.getId());

        return replyEntityList.stream().map(it->{
            return replyConverter.toDto(it);
        }).collect(Collectors.toList());
    }
}
