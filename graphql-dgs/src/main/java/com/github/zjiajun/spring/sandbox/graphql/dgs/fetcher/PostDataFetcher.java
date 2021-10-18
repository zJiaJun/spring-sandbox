package com.github.zjiajun.spring.sandbox.graphql.dgs.fetcher;

import com.github.zjiajun.spring.sandbox.graphql.dgs.context.CustomGraphqlContext;
import com.github.zjiajun.spring.sandbox.graphql.dgs.service.PostService;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Author;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Comment;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Post;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.PostQuery;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.internal.DgsWebMvcRequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:43
 */
@Slf4j
@DgsComponent
public class PostDataFetcher {

    @Autowired
    private PostService postService;

    @DgsQuery
    public Post getPostById(@InputArgument String postId) {
        return postService.getPostById(postId);
    }

    /**
     * 在schema中, 输入参数通常被定义为可选的, 可以使用Optional包装, 避免null判断
     * @param title title
     * @return posts
     */
    @DgsData(parentType = "Query", field = "getAllPosts")
    public List<Post> getAllPosts(@InputArgument("title") Optional<String> title) {
        return postService.getAllPosts(title);
    }

    /**
     * 对于input参数是集合类型时,需要指定collectionType值
     * @param postQueries post query list argument
     * @return posts
     */
    @DgsQuery
    public List<Post> getPostByListArg(@InputArgument(value = "postQueries", collectionType = PostQuery.class) List<PostQuery> postQueries) {
        return postService.getPostByListArg(postQueries);
    }

    @DgsQuery
    public String getHeaderAndCookie(@RequestHeader(value = "host") String host,
                                     @RequestHeader(value = "user-Agent") String userAgent,
                                     @CookieValue(value = "manual-cookie") String manualCookie,
                                     DgsDataFetchingEnvironment dataFetchingEnvironment) {
        String response = String.format("request header [host]: %s [user-Agent]: %s cookie [manual-cookie]: %s", host, userAgent, manualCookie);
        log.info(response);
        DgsContext dgsContext = dataFetchingEnvironment.getDgsContext();
        //获取自定义的上下文
        CustomGraphqlContext graphqlContextFromStaticMethod = DgsContext.getCustomContext(dataFetchingEnvironment);
        CustomGraphqlContext graphqlContextFromObjectMethod = (CustomGraphqlContext) dgsContext.getCustomContext();
        log.info("Custom context: {}", graphqlContextFromStaticMethod);
        log.info("DgsContext.getCustomContext == dgsContext.getCustomContext, result = [{}]", graphqlContextFromStaticMethod == graphqlContextFromObjectMethod);

        DgsWebMvcRequestData requestData = ((DgsWebMvcRequestData) dgsContext.getRequestData());
        ServletWebRequest webRequest = ((ServletWebRequest) requestData.getWebRequest());
        webRequest.getResponse().addCookie(new Cookie("response-cookie", "response-cookie-value"));
        Optional<HttpHeaders> optionalHttpHeaders = Optional.ofNullable(requestData.getHeaders());
        optionalHttpHeaders.ifPresent(httpHeaders -> httpHeaders.forEach((k, v) -> log.info("header key: {}, value: {}", k, v)));
        return response;
    }

    /**
     *
     * post的comments提供单独的dataFetcher方法
     * 只有查询post对象包含comments字段,才会执行
     * 避免查询post时,没有指定comments字段,也执行查询comments,如果查询comments很耗时,就是浪费资源
     * @param dataFetchingEnvironment fetcher context
     * @return comments
     */
    @DgsData(parentType = "Post", field = "comments")
    public List<Comment> getComment(DgsDataFetchingEnvironment dataFetchingEnvironment) {
        Post post = dataFetchingEnvironment.getSource();
        log.info("根据 postId = [{}] 查询评论", post.getId());
        return postService.getCommentByPostId(post.getId());
    }

    /**
     * 多个DgsData组合
     * 一个方法, 对应一个类型的多个字段, 或者不同类型的1到多个字段
     * @param dataFetchingEnvironment fetcher context
     * @return author
     */
    @DgsData.List({
            @DgsData(parentType = "Post", field = "author"),
            @DgsData(parentType = "Comment", field = "author")
    })
    public Author getAuthor(DgsDataFetchingEnvironment dataFetchingEnvironment) {
        Object source = dataFetchingEnvironment.getSource();
        if (source instanceof Post) {
            log.info("根据 postId = [{}] 查询作者", ((Post) source).getId());
            return postService.getAuthorByPostId(((Post) source).getId());
        }
        if (source instanceof Comment) {
            log.info("根据 commentId = [{}] 查询作者", ((Comment)source).getId());
            return postService.getAuthorByCommentId(((Comment) source).getId());
        }
        throw new IllegalArgumentException("fetch env context source must be Post or Comment");
    }
}
