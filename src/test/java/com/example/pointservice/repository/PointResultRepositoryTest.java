package com.example.pointservice.repository;

import com.example.pointservice.domain.Point;
import com.example.pointservice.domain.PointResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
class PointResultRepositoryTest {
    @Autowired
    private PointResultRepository pointResultRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    public void 포인트_결과_저장_테스트(){
        // given
        PointResult pointResult = PointResult.builder()
                .resultPoint(100L)
                .status(1)
                .build();

        // when
        PointResult savePointResult = pointResultRepository.save(pointResult);
        Long saveId = savePointResult.getId();

        Optional<PointResult> findId = pointResultRepository.findById(saveId);
        Long findPoint = findId.get().getResultPoint();
        Integer status = findId.get().getStatus();

        // then
        Assertions.assertThat(findPoint).isEqualTo(pointResult.getResultPoint());
        Assertions.assertThat(status).isEqualTo(pointResult.getStatus());
    }


}