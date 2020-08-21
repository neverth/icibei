package fun.neverth.icibei.organization.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
class GroupControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：
        // 指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        System.out.println();
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void get() throws Exception {
        DispatcherServlet dispatcherServlet = mockMvc.getDispatcherServlet();
        String s = mockMvc.perform(MockMvcRequestBuilders.get("/group/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(s);
    }

    @Test
    void query() {
    }

    @Test
    void search() {
    }

    @Test
    void testSearch() {
    }
}