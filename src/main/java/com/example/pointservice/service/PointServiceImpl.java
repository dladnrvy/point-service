package com.example.pointservice.service;

import com.example.pointservice.client.BarcodeServiceClient;
import com.example.pointservice.client.PartnerServiceClient;
import com.example.pointservice.domain.Point;
import com.example.pointservice.domain.PointResult;
import com.example.pointservice.dto.PointDto;
import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.basic.BasicResponse;
import com.example.pointservice.dto.basic.RtnCode;
import com.example.pointservice.exception.NotEnoughPointException;
import com.example.pointservice.exception.NotFoundPointException;
import com.example.pointservice.repository.PointRepository;
import com.example.pointservice.repository.PointResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointServiceImpl implements PointService{

    private final PointRepository pointRepository;
    private final PointResultRepository pointResultRepository;
    private final BarcodeServiceClient barcodeServiceClient;
    private final PartnerServiceClient partnerServiceClient;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());


    /**
     *  포인트 차감
     */
    @Override
    @Transactional
    public void usePoint(PointDto pointDto){
        Long barcodeId = pointDto.getBarcodeId();
        Long categoryId = pointDto.getCategoryId();
        Long reqPoint = pointDto.getPoint();

        //포인트 조회
        Point findPointEntity = findByBarcodeIdAndCategoryId(barcodeId, categoryId);
        if(findPointEntity == null) throw new NotFoundPointException("point not found");
        Long beforePoint = findPointEntity.getPoint();

        //포인트 저장
        findPointEntity.usePoint(reqPoint);
        Long afterPoint = findPointEntity.getPoint();
        if( beforePoint - reqPoint != afterPoint) throw new NotEnoughPointException("point use exception");

        //포인트결과저장
        PointResultDto pointResultDto = PointResultDto.builder()
                .pointId(findPointEntity.getId())
                .point(reqPoint)
                .status(2) //차감 2
                .build();
        savePointResult(pointResultDto);
    }

    /**
     *  포인트 적립
     */
    @Override
    @Transactional
    public void savePoint(PointDto pointDto){
        log.info("service");
        Long barcodeId = pointDto.getBarcodeId();
        Long categoryId = pointDto.getCategoryId();
        Long reqPoint = pointDto.getPoint();
        Long pointId;

        //포인트 조회
        Point findPointEntity = findByBarcodeIdAndCategoryId(barcodeId, categoryId);
        if(findPointEntity == null){
            //포인트 저장
            Point pointEntity = Point.builder()
                    .barcodeId(barcodeId)
                    .categoryId(categoryId)
                    .point(reqPoint)
                    .build();

            if(pointEntity == null) throw new NotFoundPointException("point not found");
            pointRepository.save(pointEntity);
            pointId = pointEntity.getId();
        } else {
            //포인트 업데이트
            findPointEntity.savePoint(reqPoint);
            pointId = findPointEntity.getId();
        }

        //포인트결과저장
        PointResultDto pointResultDto = PointResultDto.builder()
                .pointId(pointId)
                .point(reqPoint)
                .status(1) //적립 1
                .build();
        savePointResult(pointResultDto);
    }

    /**
     * BarcodeId 와 categoryId 를 통한 조회
     * @return Point
     */
    @Override
    public Point findByBarcodeIdAndCategoryId(Long barcodeId, Long categoryId) {
        Point findPointEntity = pointRepository.findByBarcodeIdAndCategoryId(barcodeId, categoryId);
        return  findPointEntity;
    }

    /**
     * pointResult 저장
     */
    @Override
    public void savePointResult(PointResultDto pointResultDto) {
        PointResult pointResult = PointResult.builder()
                .pointId(pointResultDto.getPointId())
                .point(pointResultDto.getPoint())
                .status(pointResultDto.getStatus())
                .build();
        pointResultRepository.save(pointResult);
        log.info("pointResult id : " + pointResult.getId());
    }

    /**
     * BarcodeId 조회
     * @return BarcodeId
     */
    @Override
    public BasicResponse findBarcodeId(String barcode) {
        BasicResponse getBarcode = barcodeServiceClient.getBarcodeId(barcode);
        return getBarcode;
    }

    /**
     * categoryId 조회
     * @return categoryId
     */
    @Override
    public BasicResponse findCategoryId(Long categoryId) {
        BasicResponse getCategoryId = partnerServiceClient.getCategoryId(categoryId);
        return getCategoryId;
    }




}
