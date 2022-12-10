package com.example.pointservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointResultFindRequestDto {
    private String stDate;
    private String edDate;
    private String barcode;

    public PointResultFindRequestDto(String stDate, String edDate, String barcode) {
        this.stDate = stDate;
        this.edDate = edDate;
        this.barcode = barcode;
    }
}
