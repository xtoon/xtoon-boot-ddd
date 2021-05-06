package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.util.TenantContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 日志Controller测试
 *
 * @author haoxin
 * @date 2021-02-06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class LogControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        logger.info(mockMvc.toString());
    }

    @Test
    void list() throws Exception {
        TenantContext.setTenantId("3");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/sys/log/list")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        logger.info(mvcResult.getResponse().getContentAsString());
    }

}