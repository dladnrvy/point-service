package com.example.pointservice.service;


import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.PointResultFindDto;
import com.example.pointservice.dto.ResultDto;
import com.example.pointservice.dto.ResultDtoInterface;
import com.example.pointservice.repository.PointRepository;
import com.example.pointservice.repository.PointResultRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointResultServiceImpl implements PointResultService{

    private final PointResultRepository pointResultRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * pointResult 조회
     */
    @Override
    public List<ResultDtoInterface> findPointResult(PointResultFindDto pointResultFindDto) {
        Long barcodeId = pointResultFindDto.getBarcodeId();
        String stDateStr = pointResultFindDto.getStDate();
        String edDateStr = pointResultFindDto.getEdDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime stDate = LocalDateTime.parse(stDateStr, formatter);
        LocalDateTime edDate = LocalDateTime.parse(edDateStr, formatter);

        List<ResultDtoInterface> pointResultList =  pointResultRepository.findResult(barcodeId, stDate, edDate);
        return pointResultList;
    }

}
