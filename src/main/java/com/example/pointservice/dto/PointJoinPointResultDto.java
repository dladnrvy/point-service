package com.example.pointservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PointJoinPointResultDto {
    private String date;
    private String status;
    private String partner;
    private String category;

    @Builder
    public PointJoinPointResultDto(String date, String status, String partner, String category) {
        this.date = date;
        this.status = status;
        this.partner = partner;
        this.category = category;
    }
}
