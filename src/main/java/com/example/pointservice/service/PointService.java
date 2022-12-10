package com.example.pointservice.service;


import com.example.pointservice.domain.Point;
import com.example.pointservice.dto.PointDto;
import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.basic.BasicResponse;
import org.springframework.http.ResponseEntity;

public interface PointService {
     void usePoint(PointDto pointDto);
     void savePoint(PointDto pointDto);
     BasicResponse findBarcodeId(String barcode);
     BasicResponse findCategoryId(Long categoryId);
     Point findByBarcodeIdAndCategoryId(Long barcodeId, Long categoryId);
     void savePointResult(PointResultDto pointResultDto);
}
