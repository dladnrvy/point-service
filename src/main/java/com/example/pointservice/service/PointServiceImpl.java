package com.example.pointservice.service;

import com.example.pointservice.domain.Point;
import com.example.pointservice.dto.PointDto;
import com.example.pointservice.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointServiceImpl implements PointService{

    private final ModelMapper modelMapper;
    private final PointRepository pointRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());


    /**
     *  포인트 차감
     */
    @Override
    public void usePoint(PointDto pointDto) {
        Point pointEntity = modelMapper.map(pointDto, Point.class);
        Long barcodeId = pointEntity.getBarcodeId();
        Long categoryId = pointEntity.getCategoryId();
        Long reqPoint = pointDto.getPoint();

        pointEntity = pointRepository.findByBarcodeIdAndCategoryId(barcodeId, categoryId);
        pointEntity.usePoint(reqPoint);
    }

    /**
     *  포인트 적립
     */
    @Override
    @Transactional
    public void savePoint(PointDto pointDto){
        Point pointEntity = modelMapper.map(pointDto, Point.class);
        Long barcodeId = pointEntity.getBarcodeId();
        Long categoryId = pointEntity.getCategoryId();
        Long reqPoint = pointDto.getPoint();

        pointEntity = pointRepository.findByBarcodeIdAndCategoryId(barcodeId, categoryId);
        pointEntity.savePoint(reqPoint);
    }

    /**
     * BarcodeId 조회
     * @return BarcodeId
     */
    @Override
    public Long findBarcodeId(String barcode) {
        return null;
    }

    /**
     * categoryId 조회
     * @return categoryId
     */
    @Override
    public Long findCategoryId(Long categoryId) {
        return null;
    }

    /**
     * PartnerId 조회
     * @return PartnerId
     */
    @Override
    public Long findPartnerId(Long partnerId) {
        return null;
    }


}
