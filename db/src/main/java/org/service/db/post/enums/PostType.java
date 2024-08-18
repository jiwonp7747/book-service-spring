package org.service.db.post.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PostType {

    PURCHASE("구매글"),
    SELL("판매글")
    ;

    private final String description;
}
