package com.github.zjiajun.spring.sandbox.graphql.netflix.types;

import lombok.Data;

import java.util.Date;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/17 15:13
 */
@Data
public abstract class BaseType {

    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
}
