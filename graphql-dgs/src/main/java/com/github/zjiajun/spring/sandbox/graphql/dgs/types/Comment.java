package com.github.zjiajun.spring.sandbox.graphql.dgs.types;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:08
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseType {

    private String id;
    private String postId;
    private String content;
    private Author author;

}
