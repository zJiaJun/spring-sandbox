package com.github.zjiajun.spring.sandbox.graphql.netflix.context;

import lombok.ToString;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/18 22:06
 */
@ToString
public class CustomGraphqlContext {

    private final String contextContent = "context-content-value";

    public String getContextContent() {
        return contextContent;
    }
}
