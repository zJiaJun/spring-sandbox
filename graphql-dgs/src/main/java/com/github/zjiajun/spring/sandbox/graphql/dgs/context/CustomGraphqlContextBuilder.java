package com.github.zjiajun.spring.sandbox.graphql.dgs.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/18 22:07
 *
 * DgsCustomContextBuilder 普通构建自定义上下文
 * DgsCustomContextBuilderWithRequest 带请求头和请求构建自定义上下文
 */
@Component
public class CustomGraphqlContextBuilder implements DgsCustomContextBuilderWithRequest<CustomGraphqlContext> {

    @Override
    public CustomGraphqlContext build(@Nullable Map<String, ?> map, @Nullable HttpHeaders httpHeaders, @Nullable WebRequest webRequest) {
        return new CustomGraphqlContext();
    }

}
