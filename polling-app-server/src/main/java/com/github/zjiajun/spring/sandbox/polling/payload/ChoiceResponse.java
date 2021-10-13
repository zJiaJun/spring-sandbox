package com.github.zjiajun.spring.sandbox.polling.payload;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 21:55
 */
public class ChoiceResponse {

    private long id;
    private String text;
    private long voteCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
