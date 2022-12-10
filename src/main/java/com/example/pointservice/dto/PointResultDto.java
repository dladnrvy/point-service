package com.example.pointservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointResultDto {
    private Long pointId;
    private Integer status;
    private Long point;

    @Builder
    public PointResultDto(Long pointId, Integer status, Long point) {
        this.pointId = pointId;
        this.status = status;
        this.point = point;
    }
}
