package org.service.api.domain.chatmessage.controller;

import lombok.RequiredArgsConstructor;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.chatmessage.business.ChatMessageBusiness;
import org.service.api.domain.chatmessage.model.ChatMessageDto;
import org.service.api.domain.chatmessage.model.ChatMessageRequest;
import org.service.api.domain.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-message")
public class ChatMessageController {

    private final ChatMessageBusiness chatMessageBusiness;

    @GetMapping("/get-list")
    public List<ChatMessageDto> getList(
            @UserSession User user,
            @RequestParam(name = "chat-room-id") Long chatRoomId
    ) {
        return chatMessageBusiness.getList(chatRoomId, user);
    }

    @PostMapping("/register")
    public ChatMessageDto register(
            @UserSession User user,
            @RequestBody ChatMessageRequest chatMessageRequest
            ) {
        return chatMessageBusiness.register(chatMessageRequest, user);
    }
}
