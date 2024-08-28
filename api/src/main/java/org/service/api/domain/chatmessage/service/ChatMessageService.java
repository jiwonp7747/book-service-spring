package org.service.api.domain.chatmessage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.db.ChatMessageEntity.ChatMessageEntity;
import org.service.db.ChatMessageEntity.ChatMessageRepository;
import org.service.db.ChatMessageEntity.enums.ChatMessageStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessageEntity> getChatMessagesWithThrow(Long chatRoomId) {
        return chatMessageRepository.findAllByChatRoomIdAndStatusOrderByIdAsc(chatRoomId, ChatMessageStatus.REGISTERED);
    }

    public ChatMessageEntity register(ChatMessageEntity chatMessageEntity) {
        chatMessageEntity.setRegisteredAt(LocalDateTime.now());
        chatMessageEntity.setStatus(ChatMessageStatus.REGISTERED);
        return chatMessageRepository.save(chatMessageEntity);
    }
}
