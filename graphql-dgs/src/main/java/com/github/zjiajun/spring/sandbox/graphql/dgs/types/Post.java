package com.github.zjiajun.spring.sandbox.graphql.dgs.types;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:07
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseType {

    private String id;
    private String title;
    private String content;
    private Author author;
    private List<Comment> comments;


}
