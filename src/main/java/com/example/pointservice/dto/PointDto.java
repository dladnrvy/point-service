package com.example.pointservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointDto {
    private Long barcodeId;
    private Long partnerId;
    private Long point;
}
