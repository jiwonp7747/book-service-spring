package org.service.db.post.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PostStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
