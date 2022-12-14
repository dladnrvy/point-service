package com.example.pointservice.dto;


import java.time.LocalDateTime;
import java.util.Date;


/** result 네이티브쿼리 저장 인터페이스 */
public interface ResultDtoInterface {
    String getName();
    Character getType();
    Integer getStatus();
    LocalDateTime getApproved_Dt();
}
