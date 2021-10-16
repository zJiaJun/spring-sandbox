package com.github.zjiajun.spring.sandbox.graphql.dgs;

import com.github.zjiajun.spring.sandbox.graphql.dgs.fetcher.ShowDataFetcher;
import com.github.zjiajun.spring.sandbox.graphql.dgs.service.ShowService;
import com.github.zjiajun.spring.sandbox.graphql.dgs.types.Show;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import graphql.ExecutionResult;
import graphql.com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/16 21:13
 */
@SpringBootTest(classes = { DgsAutoConfiguration.class, ShowDataFetcher.class})
public class ShowDataFetcherTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private ShowService showService;

    @BeforeEach
    public void before() {
        Mockito.when(showService.shows())
        .thenAnswer(invocationOnMock -> Lists.newArrayList(
                new Show("mock title", 2020)
        ));
    }

    @Test
    public void show() {
        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                " query {\n" +
                        "  shows(titleFilter: \"Me\") {\n" +
                        "    title\n" +
                        "    releaseYear\n" +
                        "  }\n" +
                        "}\n", "data.shows[*].title");
        assertThat(titles).contains("Dead to Me");
    }

    @Test
    public void showMock() {
        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                " query {\n" +
                        "  shows(titleFilter: \"title\") {\n" +
                        "    title\n" +
                        "    releaseYear\n" +
                        "  }\n" +
                        "}\n", "data.shows[*].title");
        assertThat(titles).contains("mock title");
    }

    @Test
    public void showWithException() {
        Mockito.when(showService.shows()).thenThrow(new RuntimeException("nothing to see here"));
        ExecutionResult result = dgsQueryExecutor.execute(" { shows { title releaseYear }}");
        assertThat(result.getErrors()).isNotEmpty();
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("java.lang.RuntimeException: nothing to see here");
    }
}
