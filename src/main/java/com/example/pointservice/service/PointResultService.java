package com.example.pointservice.service;



import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.PointResultFindDto;
import com.example.pointservice.dto.ResultDto;
import com.example.pointservice.dto.ResultDtoInterface;


import java.util.List;


public interface PointResultService {
     List<ResultDtoInterface> findPointResult(PointResultFindDto pointResultFindDto);
}
