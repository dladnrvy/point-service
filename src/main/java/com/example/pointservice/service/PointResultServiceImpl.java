package com.example.pointservice.service;

import com.example.pointservice.client.BarcodeServiceClient;
import com.example.pointservice.client.PartnerServiceClient;
import com.example.pointservice.domain.Point;
import com.example.pointservice.domain.PointResult;
import com.example.pointservice.dto.PointDto;
import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.PointResultFindDto;
import com.example.pointservice.dto.basic.BasicResponse;
import com.example.pointservice.exception.NotEnoughPointException;
import com.example.pointservice.exception.NotFoundPointException;
import com.example.pointservice.repository.PointRepository;
import com.example.pointservice.repository.PointResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PointResultFindDto> findPointResult(PointResultDto pointResultDto) {
        List<PointResultFindDto> pointResultList = new ArrayList<>();
        //1. 바코드를 통해 바코드아이디 가져오기

        //2. 바코드아이디와 시간으로 조회한 카테고리아이디로 카테고리와 상점정보 가져오기
        //ex) 2면 TYPE = B / PARTNER = 화장품, 식당

        //3. 카테고리아이디, 카테고리, 상점정보가 들어있는 List와 카테고리아이디로 List 병합
        //List<PointResultFindDto> pointResultList =  pointResultRepository.findAll();
        return pointResultList;
    }


}
