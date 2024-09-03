package org.service.api.domain.chatroom.controller;

import lombok.RequiredArgsConstructor;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.chatroom.business.ChatRoomBusiness;
import org.service.api.domain.chatroom.controller.model.ChatRoomDto;
import org.service.api.domain.chatroom.controller.model.ChatRoomRequest;
import org.service.api.domain.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-room")
public class ChatRoomController {

    private final ChatRoomBusiness chatRoomBusiness;

    @PostMapping("")
    public ChatRoomDto register(
            @UserSession User user,
            @RequestBody ChatRoomRequest request // nickname을 받는 이유? userid, email 보안적으로 넘기기 좀 그럼 따라서 이메일을 받고 서버 내에서 entity를 찾는 식으로
            ) {
        return chatRoomBusiness.register(user, request.getPostId()); // 그렇네 postId를 알면 누구 글인지 알잖어 그러면 그 게시글에서 그 사람이랑의 채팅방이니까 그대로 구현
    }

    @GetMapping("/get-test")
    public String test(
            @RequestParam Long chatRoomId
    ) {
        return chatRoomBusiness.getForTest(chatRoomId);
    }

    @GetMapping("/get-list") // 채팅방 리스트
    public List<ChatRoomDto> getList(
            @UserSession User user
    ) {
        return chatRoomBusiness.getList(user);
    }

}
