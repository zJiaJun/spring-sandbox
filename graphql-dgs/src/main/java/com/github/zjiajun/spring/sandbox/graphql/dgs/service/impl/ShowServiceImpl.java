package com.github.zjiajun.spring.sandbox.graphql.dgs.service.impl;

import com.github.zjiajun.spring.sandbox.graphql.dgs.service.ShowService;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Show;
import graphql.com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/16 21:37
 */
@Service
public class ShowServiceImpl implements ShowService {


    @Override
    public List<Show> shows() {
        return Lists.newArrayList(
                new Show("Stranger Things", 2016),
                new Show("Ozark", 2017),
                new Show("The Crown", 2016),
                new Show("Dead to Me", 2019),
                new Show("Orange is the New Black", 2013)
        );
    }
}
