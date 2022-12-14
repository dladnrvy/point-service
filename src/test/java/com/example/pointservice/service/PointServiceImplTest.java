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
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PointServiceImplTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired private PointServiceImpl pointService;
    @Autowired private PointRepository pointRepository;

    @Test
    //@Transactional
    void 포인트_동시_차감_테스트() throws InterruptedException {
        AtomicInteger cnt = new AtomicInteger();
        int excute = 20;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(excute);

        // given
        Point pointEntity = Point.builder()
                .barcodeId(0L)
                .categoryId(0L)
                .point(10000L)
                .build();
        pointRepository.save(pointEntity);

        PointDto pointDto = new PointDto();
        pointDto.setPoint(1000L);
        pointDto.setCategoryId(0L);
        pointDto.setBarcodeId(0L);
        pointDto.setPartnerId(0L);

        // when
        for(int i=0; i<excute; i++){
            service.execute(()->{
                try{
                    pointService.usePoint(pointDto);
                    cnt.getAndIncrement();
                    log.info("성공");
                }catch (ObjectOptimisticLockingFailureException oe) {
                    log.info("충돌");
                }catch (Exception e){
                    log.info(e.getMessage());
                }

                latch.countDown();
            });
        }
        latch.await();

        // then
        assertThat(cnt.get()).isEqualTo(10);
    }

    @Test
    @Transactional
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
            assertThat(barcodeId).isEqualTo(pointEntity.getBarcodeId());
            assertThat(categoryId).isEqualTo(pointEntity.getCategoryId());
            assertThat(reqPoint).isEqualTo(pointEntity.getPoint());
        } else {
            fail();
        }
    }

    @Test
    @Transactional
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
        assertThat(pointEntity.getPoint()).isEqualTo(200L);
    }

    @Test
    @Transactional
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
        assertThat(pointEntity.getPoint()).isEqualTo(2000L);
    }
}