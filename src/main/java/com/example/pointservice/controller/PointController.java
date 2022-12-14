package com.example.pointservice.controller;


import com.example.pointservice.dto.*;
import com.example.pointservice.dto.basic.BasicResponse;
import com.example.pointservice.dto.basic.RtnCode;
import com.example.pointservice.service.PointResultServiceImpl;
import com.example.pointservice.service.PointServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/point/")
public class PointController {

    private final PointServiceImpl pointService;
    private final PointResultServiceImpl pointResultService;
    private final ModelMapper modelMapper;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @PostMapping("/save")
    public ResponseEntity<BasicResponse> pointSave(@Validated @RequestBody PointRequestDto pointRequestDto){
        BasicResponse rtn = new BasicResponse<>();

        try {
            //파트너id를 통해 카테고리id 조회
            BasicResponse partnerRtn = findCategoryId(pointRequestDto.getPartnerId());
            if(partnerRtn.getCode().equals(RtnCode.FAIL)){
                partnerRtn.setData("partner not found");
                return ResponseEntity.ok(partnerRtn);
            }
            //바코드를 통해 바코드id 조회
            BasicResponse barcodeRtn = findBarcodeId(pointRequestDto.getBarcode());
            if(barcodeRtn.getCode().equals(RtnCode.FAIL)){
                barcodeRtn.setData("barcode not found");
                return ResponseEntity.ok(barcodeRtn);
            }

            PointDto pointDto = modelMapper.map(pointRequestDto, PointDto.class);
            Long barcodeId = Long.valueOf(String.valueOf(barcodeRtn.getData()));
            Long categoryId = Long.valueOf(String.valueOf(partnerRtn.getData()));
            pointDto.setBarcodeId(barcodeId);
            pointDto.setCategoryId(categoryId);
            pointDto.setPartnerId(pointRequestDto.getPartnerId());
            //포인트와 포인트결과 저장
            pointService.savePoint(pointDto);

            if(barcodeRtn.getCode().equals(RtnCode.SUCCESS) && partnerRtn.getCode().equals(RtnCode.SUCCESS)){
                rtn.setCode(RtnCode.SUCCESS);
            }
        }catch (Exception e){
            rtn.setCode(RtnCode.FAIL);
            rtn.setData(e.getMessage());
        }


        return ResponseEntity.ok(rtn);
    }

    @PostMapping("/use")
    public ResponseEntity<BasicResponse> pointUse(@Validated @RequestBody PointRequestDto pointRequestDto){
        BasicResponse rtn = new BasicResponse<>();
        try{
            //파트너id를 통해 카테고리id 조회
            BasicResponse partnerRtn = findCategoryId(pointRequestDto.getPartnerId());
            if(partnerRtn.getCode().equals(RtnCode.FAIL)){
                partnerRtn.setData("partner not found");
                return ResponseEntity.ok(partnerRtn);
            }
            //바코드를 통해 바코드id 조회
            BasicResponse barcodeRtn = findBarcodeId(pointRequestDto.getBarcode());
            if(barcodeRtn.getCode().equals(RtnCode.FAIL)){
                barcodeRtn.setData("barcode not found");
                return ResponseEntity.ok(barcodeRtn);
            }

            PointDto pointDto = modelMapper.map(pointRequestDto, PointDto.class);
            Long barcodeId = Long.valueOf(String.valueOf(barcodeRtn.getData()));
            Long categoryId = Long.valueOf(String.valueOf(partnerRtn.getData()));
            pointDto.setBarcodeId(barcodeId);
            pointDto.setCategoryId(categoryId);
            pointDto.setPartnerId(pointRequestDto.getPartnerId());
            //포인트와 포인트결과 저장
            pointService.usePoint(pointDto);

            if(barcodeRtn.getCode().equals(RtnCode.SUCCESS) && partnerRtn.getCode().equals(RtnCode.SUCCESS)){
                rtn.setCode(RtnCode.SUCCESS);
            }
        }catch (Exception e){
            rtn.setCode(RtnCode.FAIL);
            rtn.setData(e.getMessage());
        }
        return ResponseEntity.ok(rtn);
    }

    @PostMapping("/result/find")
    public ResponseEntity<BasicResponse> findPointResult(@Validated @RequestBody PointResultFindRequestDto pointResultRequestDto){
        BasicResponse rtn = new BasicResponse<>();
        try{
            //바코드를 통해 바코드id 조회
            BasicResponse barcodeRtn = findBarcodeId(pointResultRequestDto.getBarcode());
            if(barcodeRtn.getCode().equals(RtnCode.FAIL)){
                barcodeRtn.setData("barcode not found");
                return ResponseEntity.ok(barcodeRtn);
            }

            Long barcodeId = Long.valueOf(String.valueOf(barcodeRtn.getData()));
            PointResultFindDto pointResultFindDto = PointResultFindDto.builder()
                    .barcodeId(barcodeId)
                    .stDate(pointResultRequestDto.getStDate())
                    .edDate(pointResultRequestDto.getEdDate())
                    .build();

            List<ResultDtoInterface> result = pointResultService.findPointResult(pointResultFindDto);
            if(!result.isEmpty()){
                rtn.setCode(RtnCode.SUCCESS);
                rtn.setData(result);
            } else {
                rtn.setCode(RtnCode.FAIL);
            }
        }catch (Exception e){
            rtn.setCode(RtnCode.FAIL);
            rtn.setData(e.getMessage());
        }

        return ResponseEntity.ok(rtn);
    }

    //파트너id를 통해 카테고리id 조회
    private BasicResponse findCategoryId(Long partnerId){
        BasicResponse partnerRtn = pointService.findCategoryId(partnerId);
        return partnerRtn;
    }

    //바코드를 통해 바코드id 조회
    private BasicResponse findBarcodeId(String barcodeId){
        BasicResponse barcodeRtn = pointService.findBarcodeId(barcodeId);
        return barcodeRtn;
    }


}
