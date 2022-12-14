package com.example.pointservice.controller;

import com.example.pointservice.domain.Point;
import com.example.pointservice.dto.PointRequestDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PointControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @Autowired private PointController PointController;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(PointController).build();
    }

    @Test
    void 포인트저장_유효성() throws Exception {
        // given
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/point/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content("{\"partnerId\": 0,\"barcode\": \"\", \"point\": 0}"))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    void 포인트차감_유효성() throws Exception {
        // given
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/point/use")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content("{\"partnerId\": 0,\"barcode\": \"\", \"point\": 0}"))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    void 포인트조회_유효성() throws Exception {
        // given
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/point/result/find")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content("{\"edDate\": \"\", \"barcode\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}