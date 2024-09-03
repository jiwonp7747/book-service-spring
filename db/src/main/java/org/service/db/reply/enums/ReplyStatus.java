package org.service.db.reply.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReplyStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
