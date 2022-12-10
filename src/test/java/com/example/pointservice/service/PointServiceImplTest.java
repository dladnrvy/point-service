package com.example.pointservice.service;

import com.example.pointservice.domain.Point;
import com.example.pointservice.dto.PointDto;
import com.example.pointservice.exception.NotFoundPointException;
import com.example.pointservice.repository.PointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class PointServiceImplTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @InjectMocks private PointServiceImpl pointService;
    @Autowired private PointRepository pointRepository;

    @Test
    void 포인트_증가_신규_저장() {
        // given
        PointDto pointDto = new PointDto();
        pointDto.setPoint(100L);
        pointDto.setCategoryId(123L);
        pointDto.setBarcodeId(1L);

        Long barcodeId = pointDto.getBarcodeId();
        Long categoryId = pointDto.getCategoryId();
        Long reqPoint = pointDto.getPoint();

        // when
        Point findPointEntity = pointRepository.findByBarcodeIdAndCategoryId(barcodeId, categoryId);

        if(findPointEntity == null){
            Point pointEntity = Point.builder()
                    .barcodeId(barcodeId)
                    .categoryId(categoryId)
                    .point(reqPoint)
                    .build();

            if(pointEntity == null) throw new NotFoundPointException("point not found");
            pointRepository.save(pointEntity);

            //then
            Assertions.assertThat(barcodeId).isEqualTo(pointEntity.getBarcodeId());
            Assertions.assertThat(categoryId).isEqualTo(pointEntity.getCategoryId());
            Assertions.assertThat(reqPoint).isEqualTo(pointEntity.getPoint());
        } else {
            fail();
        }
    }

    @Test
    void 포인트_증가_업데이트() {
        // given
        Point pointEntity = Point.builder()
                .barcodeId(1L)
                .categoryId(123L)
                .point(100L)
                .build();
        pointRepository.save(pointEntity);

        // when
        Point findPointEntity = pointRepository.findByBarcodeIdAndCategoryId(1L, 123L);
        Long reqPoint = 100L;

        if(findPointEntity == null){
           fail();
        } else {
            pointEntity.savePoint(reqPoint);
        }

        // then
        Assertions.assertThat(pointEntity.getPoint()).isEqualTo(200L);
    }

    @Test
    void 포인트_감소_업데이트() {
        // given
        Point pointEntity = Point.builder()
                .barcodeId(1L)
                .categoryId(123L)
                .point(3000L)
                .build();
        pointRepository.save(pointEntity);

        // when
        Point findPointEntity = pointRepository.findByBarcodeIdAndCategoryId(1L, 123L);
        Long reqPoint = 1000L;

        if(findPointEntity == null){
            fail();
        } else {
            pointEntity.usePoint(reqPoint);
        }

        // then
        Assertions.assertThat(pointEntity.getPoint()).isEqualTo(2000L);
    }

    @Test
    void findBarcodeId() {
    }

    @Test
    void findCategoryId() {
    }

    @Test
    void findPartnerId() {
    }
}