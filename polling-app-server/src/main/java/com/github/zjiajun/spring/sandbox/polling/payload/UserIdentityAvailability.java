package com.github.zjiajun.spring.sandbox.polling.payload;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 21:53
 */
public class UserIdentityAvailability {

    private Boolean available;

    public UserIdentityAvailability(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
