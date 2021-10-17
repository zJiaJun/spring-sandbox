package com.github.zjiajun.spring.sandbox.graphql.dgs.service;

import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Author;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Comment;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Post;
import graphql.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:20
 */
@Slf4j
@Service
public class PostService {

    private List<Post> posts = new ArrayList<>();
    private Map<String,List<Comment>> commentsMap = new HashMap<>();
    private Map<String, Author> postAuthorMap = new HashMap<>();
    private Map<String, Author> commentAuthorMap = new HashMap<>();

    @PostConstruct
    public void initData() {
        posts = Lists.newArrayList(
                Post.builder().id("10").title("t1").content("c1").build(),
                Post.builder().id("20").title("t2").content("c2").build(),
                Post.builder().id("30").title("t3").content("c3").build()
        );

        commentsMap.put("10", Lists.newArrayList(
                Comment.builder().id("100").postId("10").content("comment-content10_A").build(),
                Comment.builder().id("110").postId("10").content("comment-content10_B").build()
        ));
        commentsMap.put("20", Lists.newArrayList(
                Comment.builder().id("200").postId("20").content("comment-content20_A").build(),
                Comment.builder().id("210").postId("20").content("comment-content20_B").build()
        ));
        commentsMap.put("30", Lists.newArrayList(
                Comment.builder().id("300").postId("30").content("comment-content30_A").build(),
                Comment.builder().id("310").postId("30").content("comment-content30_B").build()
        ));

        postAuthorMap.put("10", Author.builder().id("1000").name("Jay").build());

        commentAuthorMap.put("100", Author.builder().id("10000").name("Leon").build());
        commentAuthorMap.put("110", Author.builder().id("20000").name("Tom").build());

    }

    public Post getPostById(String postId) {
        return posts.stream()
                .filter(p -> p.getId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Argument postId is wrong"));
    }

    public List<Post> getAllPosts(String title) {
        return posts.stream()
                .filter(p -> (title == null || "".equals(title)) || p.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentByPostId(String postId) {
        return commentsMap.get(postId);
    }

    public Author getAuthorByPostId(String postId) {
        return postAuthorMap.get(postId);
    }

    public Author getAuthorByCommentId(String commentId) {
        return commentAuthorMap.get(commentId);
    }
}
