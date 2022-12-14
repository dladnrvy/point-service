package com.example.pointservice.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ToString
public class PointResultFindRequestDto {
    @NotNull
    private String stDate;
    @NotNull
    private String edDate;
    @NotNull
    private String barcode;

}
