package com.example.pointservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

/** 관계형 쿼리 저장 dto */
@Data
public class ResultDto {
    private Long categoryId;
    private Integer status;
    private LocalDateTime approvedDt;

    public ResultDto(Long categoryId, Integer status, LocalDateTime approvedDt) {
        this.categoryId = categoryId;
        this.status = status;
        this.approvedDt = approvedDt;
    }


}
