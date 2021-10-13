package com.github.zjiajun.spring.sandbox.polling.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 21:50
 */
public class ChoiceRequest {

    @NotBlank
    @Size(max = 40)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
