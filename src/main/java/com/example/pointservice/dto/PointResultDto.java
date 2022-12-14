package com.example.pointservice.dto;

import com.example.pointservice.domain.Point;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointResultDto {
    private Point pointId;
    private Integer status;
    private Long point;
    private Long partnerId;

    @Builder
    public PointResultDto(Point pointId, Integer status, Long point, Long partnerId) {
        this.pointId = pointId;
        this.status = status;
        this.point = point;
        this.partnerId = partnerId;
    }
}
