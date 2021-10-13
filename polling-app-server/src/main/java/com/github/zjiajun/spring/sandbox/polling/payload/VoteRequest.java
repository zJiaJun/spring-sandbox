package com.github.zjiajun.spring.sandbox.polling.payload;

import javax.validation.constraints.NotNull;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 21:52
 */
public class VoteRequest {

    @NotNull
    private Long choiceId;

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }
}
