package org.service.api.domain.chatroom.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {

    private Long id;

    private String anotherUserNickname;

    private String imageUrl;

}
