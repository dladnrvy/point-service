package com.example.pointservice.repository;

import com.example.pointservice.domain.Barcode;
import com.example.pointservice.domain.Point;
import com.example.pointservice.exception.NotEnoughPointException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    public void 포인트_적립_테스트(){
        // given
        Point point = Point.builder()
                .point(10000L)
                .barcodeId(1L)
                .categoryId(1L)
                .build();

        // when
        Point savePoint = pointRepository.save(point);
        Long saveId = savePoint.getId();

        Optional<Point> findId = pointRepository.findById(saveId);
        Long findBarcodeId = findId.get().getBarcodeId();
        Long findCategoryId = findId.get().getCategoryId();
        Long findPoint = findId.get().getPoint();

        // then
        Assertions.assertThat(findBarcodeId).isEqualTo(point.getBarcodeId());
        Assertions.assertThat(findCategoryId).isEqualTo(point.getCategoryId());
        Assertions.assertThat(findPoint).isEqualTo(point.getPoint());
    }

    @Test
    public void 포인트_증가_테스트(){
        // given
        Long beforePoint = 1000L;
        Point point = Point.builder()
                .point(beforePoint)
                .barcodeId(1L)
                .categoryId(1L)
                .build();

        point.savePoint(1000L);
        Point beforeSavePointData = pointRepository.save(point);

        // when
        Optional<Point> findId = pointRepository.findById(beforeSavePointData.getId());
        Long findPoint = findId.get().getPoint();
        Long findBarcodeId = findId.get().getBarcodeId();
        Long findCategoryId = findId.get().getCategoryId();

        // then
        Assertions.assertThat(findBarcodeId).isEqualTo(findBarcodeId);
        Assertions.assertThat(findCategoryId).isEqualTo(findCategoryId);
        Assertions.assertThat(findPoint).isEqualTo(2000L);
    }

    @Test
    public void 포인트_감소_테스트(){
        // given
        Long beforePoint = 3000L;
        Point point = Point.builder()
                .point(beforePoint)
                .barcodeId(1L)
                .categoryId(1L)
                .build();

        point.usePoint(1000L);
        Point beforeSavePointData = pointRepository.save(point);

        // when
        Optional<Point> findId = pointRepository.findById(beforeSavePointData.getId());
        Long findPoint = findId.get().getPoint();
        Long findBarcodeId = findId.get().getBarcodeId();
        Long findCategoryId = findId.get().getCategoryId();

        // then
        Assertions.assertThat(findBarcodeId).isEqualTo(findBarcodeId);
        Assertions.assertThat(findCategoryId).isEqualTo(findCategoryId);
        Assertions.assertThat(findPoint).isEqualTo(2000);
    }

    @Test
    public void 포인트_감소_오류_테스트(){
        // given
        Long beforePoint = 1000L;
        Point point = Point.builder()
                .point(beforePoint)
                .barcodeId(1L)
                .categoryId(1L)
                .build();

        Assertions.assertThatThrownBy(()-> point.usePoint(1500L)).isInstanceOf(NotEnoughPointException.class);
    }

}