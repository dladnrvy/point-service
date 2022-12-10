package com.example.pointservice.repository;

import com.example.pointservice.domain.PointResult;
import com.example.pointservice.dto.PointResultFindDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PointResultRepository extends JpaRepository<PointResult, Long> {
    @Query(value = "select DISTINCT c.categoryId " +
            "from Point c " +
            "left join PointResult p " +
            "where c.barcodeId = :barcodeId " +
            "and p.approvedDt > :stDate " +
            "and p.approvedDt < :edDate")
    List<Long> findCategoryId(@Param("barcodeId") Long barcodeId, @Param("stDate") String stDate, @Param("edDate") String edDate);
}
