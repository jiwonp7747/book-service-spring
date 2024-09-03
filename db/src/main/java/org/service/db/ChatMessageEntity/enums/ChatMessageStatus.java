package org.service.db.ChatMessageEntity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ChatMessageStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}