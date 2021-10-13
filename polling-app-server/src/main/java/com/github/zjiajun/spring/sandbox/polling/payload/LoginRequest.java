package com.github.zjiajun.spring.sandbox.polling.payload;

import javax.validation.constraints.NotBlank;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/12 21:49
 */
public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
