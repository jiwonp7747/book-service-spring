package org.service.api.domain.chatmessage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageRequest {

    private String content;

    private Long chatRoomId;
}
