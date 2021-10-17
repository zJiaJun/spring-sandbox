package com.github.zjiajun.spring.sandbox.graphql.dgs.fetcher;

import com.github.zjiajun.spring.sandbox.graphql.dgs.service.PostService;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Author;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Comment;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Post;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:43
 */
@DgsComponent
public class PostDataFetcher {

    @Autowired
    private PostService postService;

    @DgsQuery
    public Post getPostById(@InputArgument String postId) {
        return postService.getPostById(postId);
    }

    @DgsData(parentType = "Query", field = "getAllPosts")
    public List<Post> getAllPosts(@InputArgument("title") String title) {
        return postService.getAllPosts(title);
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
            return postService.getAuthorByPostId(((Post) source).getId());
        }
        if (source instanceof Comment) {
            return postService.getAuthorByCommentId(((Comment) source).getId());
        }
        throw new IllegalArgumentException("fetch env context source must be Post or Comment");
    }
}
