package com.example.pointservice.repository;

import com.example.pointservice.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;


@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    /** 포인트 조회 Lock 설정 */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Point findByBarcodeIdAndCategoryId(Long barcodeId, Long partnerId);

}
