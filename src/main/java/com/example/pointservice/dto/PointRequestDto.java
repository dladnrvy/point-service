package com.example.pointservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@ToString
public class PointRequestDto {

    @NotNull(message = "PARTNER_ID_IS_MANDATORY")
    private Long partnerId;

    @NotNull(message = "BARCODE_IS_MANDATORY")
    private String barcode;

    @NotNull(message = "POINT_IS_MANDATORY")
    @Positive
    private Long point;


}
