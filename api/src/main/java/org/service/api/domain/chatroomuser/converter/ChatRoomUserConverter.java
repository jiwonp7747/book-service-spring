package org.service.api.domain.chatroomuser.converter;

import org.service.db.chatroomuser.ChatRoomUserEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomUserConverter {


    public ChatRoomUserEntity toEntity(Long chatRoomId, Long userId) {

        return ChatRoomUserEntity.builder()
                .chatRoomId(chatRoomId)
                .userId(userId)
                .build()
                ;
    }
}
