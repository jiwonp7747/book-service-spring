package org.service.db.chatroom.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ChatRoomStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
