package org.service.api.domain.chatmessage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.ChatMessageEntity.enums.ChatMessageStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long id;

    private String content;

    private LocalDateTime registeredAt;

    private Long chatRoomId;

    private Boolean isMe;
}
