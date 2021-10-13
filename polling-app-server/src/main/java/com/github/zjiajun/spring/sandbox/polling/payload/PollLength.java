package com.github.zjiajun.spring.sandbox.polling.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 21:51
 */
public class PollLength {

    @NotNull
    @Max(7)
    private Integer days;

    @NotNull
    @Max(23)
    private Integer hours;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
