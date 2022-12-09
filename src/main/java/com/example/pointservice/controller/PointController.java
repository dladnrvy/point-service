package com.example.pointservice.controller;


import com.example.pointservice.dto.*;
import com.example.pointservice.dto.basic.BasicResponse;
import com.example.pointservice.dto.basic.ErrorResponse;
import com.example.pointservice.service.PointServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point/")
public class PointController {

    private final PointServiceImpl pointService;
    private final ModelMapper modelMapper;

    @GetMapping("/test")
    public String test(){
        return String.format("It's Working in Point Service");
    }

    @PostMapping("/save")
    public ResponseEntity<? extends BasicResponse> pointSave(@Validated @RequestBody PointRequestDto pointRequestDto){
        Map<String, Long> pointChkMap = pointCheck(pointRequestDto);
        if(pointChkMap.get("barcodeRtn").equals(null)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("등록 되지 않은 바코드입니다."));
        else if(pointChkMap.get("partnerRtn").equals(null)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("등록 되지 않은 상점입니다."));
        else{
            Long barcodeId = pointChkMap.get("barcodeRtn");
            PointDto pointDto = modelMapper.map(pointRequestDto, PointDto.class);
            pointDto.setBarcodeId(barcodeId);
            pointService.savePoint(pointDto);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/use")
    public ResponseEntity<? extends BasicResponse> pointUse(@Validated @RequestBody PointRequestDto pointRequestDto){

        Map<String, Long> pointChkMap = pointCheck(pointRequestDto);
        if(pointChkMap.get("barcodeRtn").equals(null)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("등록 되지 않은 바코드입니다."));
        else if(pointChkMap.get("partnerRtn").equals(null)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("등록 되지 않은 상점입니다."));
        else{
            Long barcodeId = pointChkMap.get("barcodeRtn");
            PointDto pointDto = modelMapper.map(pointRequestDto, PointDto.class);
            pointDto.setBarcodeId(barcodeId);
            pointService.usePoint(pointDto);
        }

        return ResponseEntity.ok().build();
    }

    //바코드, 상점아이디값 체크함수
    public Map<String, Long> pointCheck(PointRequestDto pointRequestDto){
        Map<String, Long> map = new HashMap<>();

        Long barcodeId = pointService.findBarcodeId(pointRequestDto.getBarcode());
        if(barcodeId == null) {
            map.put("barcodeRtn", null);
            return map;
        } else {
            map.put("barcodeRtn", barcodeId);
        }

        Long partnerId = pointService.findPartnerId(pointRequestDto.getPartnerId());
        if(partnerId == null) map.put("partnerRtn", null);

        return map;
    }

}
