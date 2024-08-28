package org.service.api.domain.chatroom.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.chatroom.controller.model.ChatRoomDto;
import org.service.api.domain.chatroom.converter.ChatRoomConveter;
import org.service.api.domain.chatroom.service.ChatRoomService;
import org.service.api.domain.chatroomuser.converter.ChatRoomUserConverter;
import org.service.api.domain.chatroomuser.service.ChatRoomUserService;
import org.service.api.domain.post.service.PostService;
import org.service.api.domain.user.model.User;
import org.service.api.domain.user.service.UserService;
import org.service.db.chatroom.ChatRoomRepository;
import org.service.db.chatroom.enums.ChatRoomStatus;
import org.service.db.chatroomuser.ChatRoomUserRepository;
import org.service.db.chatroomuser.enums.ChatRoomUserStatus;
import org.service.db.image.ImageRepository;
import org.service.db.post.PostRepository;
import org.service.db.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomBusiness {

    private final ChatRoomService chatRoomService;
    private final PostService postService;

    private final ChatRoomConveter chatRoomConveter;
    private final ChatRoomUserConverter chatRoomUserConverter;

    private final PostRepository postRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomRepository chatRoomRepository; //TODO 테스트를 위한 레포 나중에 삭제

    private final ImageRepository imageRepository;

    private final UserService userService;
    private final ChatRoomUserService chatRoomUserService;

    public ChatRoomDto register(User user, Long postId) {
        var postEntity=postService.getPostWithThrow(postId);
        // 게시글에 해당하는 채팅방 만들기
        var entity=chatRoomConveter.toEntity(user, postEntity);
        var newChatRoomEntity=chatRoomService.register(entity);

        // 사용자 본인 테이블 매핑용 entity 만들기 및 저장
        var chatRoomUserEntity=chatRoomUserConverter.toEntity(newChatRoomEntity.getId(), user.getId());
        var newChatRoomUserEntity=chatRoomUserService.register(chatRoomUserEntity);

        // 상대 테이블 매핑용 entity 만들기
        // 게시글을 작성할 상대와 대화를 할 것이기 때문
        var anotherUserId=postEntity.getUserId();
        var chatRoomAnotherUserEntity=chatRoomUserConverter.toEntity(newChatRoomEntity.getId(), anotherUserId);
        var newChatRoomAnotherUserEntity=chatRoomUserService.register(chatRoomAnotherUserEntity);

        // 클라이언트에 내릴 데이터 생성
        var imageEntity=imageRepository.findFirstByPostIdOrderByIdDesc(postId).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
        var imageUrl=imageEntity.getUrl();

        var anotherUserEntity=userService.getUserWithThrow(anotherUserId);

        return chatRoomConveter.toDto(newChatRoomEntity, anotherUserEntity.getNickname(), imageUrl);
    }

    // 토큰 접근
    // ChatRoom 하나 가져오는 코드 manytoone 작동하는지 체크
    public String getForTest(Long chatRoomId) {
        var entity=chatRoomRepository.findById(chatRoomId).get();
        return entity.getPost().getTitle();

        //return chatRoomConveter.toDto(entity);

    }

    public List<ChatRoomDto> getList(User user) {
        var chatRoomUserList=chatRoomUserRepository.findAllByUserIdAndStatusOrderByIdDesc(user.getId(), ChatRoomUserStatus.REGISTERED);

        return chatRoomUserList.stream().map(it->{
            var chatRoomId=it.getChatRoomId();
            log.info("chatRoomId={}",chatRoomId);
            var chatRoomEntity=chatRoomService.getWithThrow(chatRoomId);

            var postEntity=chatRoomEntity.getPost();

            var userId=postEntity.getUserId();
            var userEntity=userService.getUserWithThrow(userId);
            var anotherUserNickname=userEntity.getNickname();

            var imageEntity=imageRepository.findFirstByPostIdOrderByIdDesc(postEntity.getId()).get();

            return chatRoomConveter.toDto(chatRoomEntity ,anotherUserNickname, imageEntity.getUrl());
        }).collect(Collectors.toList());
    }

}
