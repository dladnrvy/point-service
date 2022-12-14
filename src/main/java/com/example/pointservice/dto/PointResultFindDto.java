package com.example.pointservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointResultFindDto {
    private String stDate;
    private String edDate;
    private Long barcodeId;

    @Builder
    public PointResultFindDto(String stDate, String edDate, Long barcodeId) {
        this.stDate = stDate;
        this.edDate = edDate;
        this.barcodeId = barcodeId;
    }
}
