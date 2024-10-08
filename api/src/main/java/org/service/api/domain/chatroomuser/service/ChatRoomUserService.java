package org.service.api.domain.chatroomuser.service;

import lombok.RequiredArgsConstructor;
import org.service.db.chatroomuser.ChatRoomUserEntity;
import org.service.db.chatroomuser.ChatRoomUserRepository;
import org.service.db.chatroomuser.enums.ChatRoomUserStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomUserService {

    private final ChatRoomUserRepository chatRoomUserRepository;

    public ChatRoomUserEntity register(ChatRoomUserEntity chatRoomUserEntity) {
        chatRoomUserEntity.setStatus(ChatRoomUserStatus.REGISTERED);
        return chatRoomUserRepository.save(chatRoomUserEntity);
    }
}
