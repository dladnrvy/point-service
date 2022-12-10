package com.example.pointservice.repository;

import com.example.pointservice.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByBarcodeIdAndCategoryId(Long barcodeId, Long partnerId);
}
