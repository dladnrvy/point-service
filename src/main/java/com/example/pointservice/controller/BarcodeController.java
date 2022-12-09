package com.example.pointservice.controller;

import com.example.pointservice.domain.Barcode;
import com.example.pointservice.dto.BarcodeCreateDto;
import com.example.pointservice.dto.BarcodeCreateRequestDto;
import com.example.pointservice.dto.BarcodeCreateResponseDto;
import com.example.pointservice.service.BarcodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/barcode/")
public class BarcodeController {

    private final BarcodeServiceImpl barcodeServiceImpl;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity createBarcode(@Validated @RequestBody BarcodeCreateRequestDto barcodeReqDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("오류 발생");
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            // 200 response with 400 status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        BarcodeCreateDto barcodeCreateDto = modelMapper.map(barcodeReqDto, BarcodeCreateDto.class);

        BarcodeCreateResponseDto barcodeCreateResponseDto = barcodeServiceImpl.createCode(barcodeCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(barcodeCreateResponseDto.getBarcode());
    }
}
