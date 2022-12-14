package com.example.pointservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PointDto {
    private Long barcodeId;
    private Long categoryId;
    private Long point;
    private Long partnerId;

}
