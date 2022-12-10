package com.example.pointservice.service;



import com.example.pointservice.dto.PointResultDto;
import com.example.pointservice.dto.PointResultFindDto;

import java.util.List;


public interface PointResultService {
     List<PointResultFindDto> findPointResult(PointResultDto pointResultDto);
}
