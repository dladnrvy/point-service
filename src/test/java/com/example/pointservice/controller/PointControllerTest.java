package com.example.pointservice.controller;

import com.example.pointservice.domain.Point;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PointControllerTest {

    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private PointController PointController;



    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(PointController).build();
    }

    @Test
    void 적립_바코드_상점명_체크_테스트() throws Exception {
        // given
        String content = mapper.writeValueAsString(new Point(1l, 1l, 1l, 1l));

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/point/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(content))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    void 사용_바코드_상점명_체크_테스트() throws Exception {
        // given
        String content = mapper.writeValueAsString(new Point(1l, 1l, 1l, 1l));

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/point/use")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(content))
                .andExpect(status().isBadRequest())
                .andDo(print());
        // then
    }

    @Test
    void 포인트_적립_테스트() throws Exception {
        // given
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/barcode/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content("{\"userId\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
        // then
    }

    @Test
    void 포인트_사용_테스트() throws Exception {
        // given
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/barcode/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content("{\"userId\":\"testString\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
        // then
    }


}