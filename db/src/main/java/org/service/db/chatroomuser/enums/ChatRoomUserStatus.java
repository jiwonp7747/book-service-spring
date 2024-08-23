package org.service.db.chatroomuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ChatRoomUserStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
