package com.example.pointservice.service;


import com.example.pointservice.dto.PointDto;

public interface PointService {
     void usePoint(PointDto pointDto);
     void savePoint(PointDto pointDto);
     Long findBarcodeId(String barcode);
     Long findCategoryId(Long categoryId);
     Long findPartnerId(Long partnerId);
}
