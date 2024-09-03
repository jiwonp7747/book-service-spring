package org.service.api.domain.chatmessage.converter;

import jakarta.persistence.*;
import org.service.api.domain.chatmessage.model.ChatMessageDto;
import org.service.api.domain.chatmessage.model.ChatMessageRequest;
import org.service.api.domain.user.model.User;
import org.service.db.ChatMessageEntity.ChatMessageEntity;
import org.service.db.ChatMessageEntity.enums.ChatMessageStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatMessageConverter {

    public ChatMessageDto toDto(ChatMessageEntity entity) {
        return ChatMessageDto.builder()
                .id(entity.getId())
                .chatRoomId(entity.getChatRoomId())
                .registeredAt(entity.getRegisteredAt())
                .content(entity.getContent())
                .build()
                ;
    }

    public ChatMessageEntity toEntity(ChatMessageRequest request, User user) {
        return ChatMessageEntity.builder()
                .userId(user.getId())
                .chatRoomId(request.getChatRoomId())
                .content(request.getContent())
                .build()
                ;
    }
}
