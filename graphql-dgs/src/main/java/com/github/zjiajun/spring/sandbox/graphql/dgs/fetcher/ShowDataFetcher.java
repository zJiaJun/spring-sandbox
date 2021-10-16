package com.github.zjiajun.spring.sandbox.graphql.dgs.fetcher;

import com.github.zjiajun.spring.sandbox.graphql.dgs.service.ShowService;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/16 20:53
 */
@DgsComponent
public class ShowDataFetcher {

    @Autowired
    private ShowService showService;

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        return showService.shows().stream().filter(s -> s.getTitle().contains(titleFilter)).collect(Collectors.toList());
    }

}
