package com.example.pagenumbersreducer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PageControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void reducePages_validParameter_returnResult() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/reducedPageNumbers")
                        .param("rawPageNumbers", "1, 5, 3, 8, 6")
                        .param("bookId", "111")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.original", is("1,5,3,8,6")))
                .andExpect(jsonPath("$.reduced", is("1,3,5-6,8")))
                .andExpect(jsonPath("$.bookId", is("111")));
    }

    @Test
    void reducePages_notValidArgument_returnBadRequest() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/reducedPageNumbers")
                                .param("rawPageNumbers", "-1")
                                .param("bookId", "12")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }


    @Test
    void reducePages_notValidArgument_returnInternalServerError() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/reducedPageNumbers")
                )
                .andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(status().isInternalServerError());
    }
}