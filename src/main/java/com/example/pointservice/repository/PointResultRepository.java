package com.example.pointservice.repository;

import com.example.pointservice.domain.Point;
import com.example.pointservice.domain.PointResult;
import com.example.pointservice.dto.ResultDto;
import com.example.pointservice.dto.ResultDtoInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PointResultRepository extends JpaRepository<PointResult, Long> {

    @Query(value =
            "SELECT T.NAME, C.TYPE, R.STATUS, R.APPROVED_DT\n" +
            "FROM POINTS AS P \n" +
            "JOIN POINT_RESULT AS R\n" +
            "ON P.POINTS_ID = R.POINTS_ID\n" +
            "JOIN CATEGORY AS C\n" +
            "ON P.CATEGORY_ID = C.ID\n" +
            "JOIN PARTNER AS T\n" +
            "ON R.PARTNER_ID = T.ID\n" +
            "WHERE P.BARCODE_ID = :barcodeId\n" +
            "AND R.APPROVED_DT > :stDate\n" +
            "AND R.APPROVED_DT < :edDate"
            , nativeQuery = true)
    List<ResultDtoInterface> findResult(@Param("barcodeId") Long barcodeId, @Param("stDate") LocalDateTime stDate, @Param("edDate") LocalDateTime edDate);

    @Query(
            value = "select new com.example.pointservice.dto.ResultDto(p.categoryId, r.status, r.approvedDt)" +
            "from PointResult r join r.pointId p " +
            "where p.barcodeId = :barcodeId and r.approvedDt > :stDate and r.approvedDt < :edDate"
    )
    List<ResultDto> findResultData(@Param(value = "barcodeId") Long barcodeId, @Param("stDate") LocalDateTime stDate, @Param("edDate") LocalDateTime edDate);
}
