package com.example.pointservice.service;

import com.example.pointservice.domain.Barcode;
import com.example.pointservice.dto.BarcodeCreateDto;
import com.example.pointservice.dto.BarcodeCreateRequestDto;
import com.example.pointservice.dto.BarcodeCreateResponseDto;

import java.util.List;

public interface BarcodeService {

     BarcodeCreateResponseDto createCode(BarcodeCreateDto barcodeCreateDto);
}
