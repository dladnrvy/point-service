package com.example.pointservice.service;

import com.example.pointservice.domain.Point;
import com.example.pointservice.domain.PointResult;
import com.example.pointservice.dto.ResultDto;
import com.example.pointservice.dto.ResultDtoInterface;
import com.example.pointservice.repository.PointRepository;
import com.example.pointservice.repository.PointResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class PointResultServiceImplTest {


    @Autowired private PointRepository pointRepository;
    @Autowired private PointResultRepository pointResultRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    void 내역_조회_테스트_네이티브쿼리_조회() {
        // 테스트 진행전
        // barcode table id 1의 저장 된 데이터
        // category 와 partner table 의 더미데이터가 저장되어 있어야 함
        // given
        Point point = Point.builder()
                .barcodeId(1L)
                .categoryId(2L)
                .point(1L)
                .build();
        pointRepository.save(point);

        PointResult pointResult = PointResult.builder()
                .status(1)
                .pointId(point)
                .resultPoint(1000L)
                .partnerId(3L)
                .build();
        pointResultRepository.save(pointResult);

        LocalDateTime now = LocalDateTime.now();
        //String edDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime minusOneDay = now.minusDays(1);
        //String stDate = minusOneDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Long barcodeId = 1L;

        // when
        List<ResultDtoInterface> resultDto =  pointResultRepository.findResult(barcodeId, minusOneDay, now);

        // then
        for(ResultDtoInterface dto : resultDto) {
            log.info("getType : "+dto.getType());
            log.info("getStatus : "+dto.getStatus());
            log.info("getApprovedDt : "+dto.getApproved_Dt());
            log.info("getName : "+dto.getName());
        }
    }

    @Test
    void 내역_조회_테스트_조회() {
        // 테스트 진행전
        // barcode table id 1의 저장 된 데이터
        // category 와 partner table 의 더미데이터가 저장되어 있어야 함
        // given
        Point point = Point.builder()
                .barcodeId(1L)
                .categoryId(2L)
                .point(1L)
                .build();
        pointRepository.save(point);

        PointResult pointResult = PointResult.builder()
                .status(1)
                .partnerId(point.getId())
                .pointId(point)
                .resultPoint(1000L)
                .build();
        pointResultRepository.save(pointResult);

        LocalDateTime now = LocalDateTime.now();
        //String edDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime minusOneDay = now.minusDays(1);
        //String stDate = minusOneDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Long barcodeId = 1L;

        // when
        List<ResultDto> resultDto =  pointResultRepository.findResultData(barcodeId, minusOneDay, now);

        // then
        for(ResultDto dto : resultDto) {
            log.info("dto : "+dto);
        }

    }
}