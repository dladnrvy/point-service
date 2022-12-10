package com.example.pointservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointResultFindDto {
    private String date;
    private String status;
    private String partner;
    private String category;

    @Builder
    public PointResultFindDto(String date, String status, String partner, String category) {
        this.date = date;
        this.status = status;
        this.partner = partner;
        this.category = category;
    }
}
