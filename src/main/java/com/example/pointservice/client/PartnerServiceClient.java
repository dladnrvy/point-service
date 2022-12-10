package com.example.pointservice.client;

import com.example.pointservice.dto.basic.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="partner-service")
public interface PartnerServiceClient {
    @GetMapping("/partner/find")
    BasicResponse getCategoryId(@RequestParam Long partnerId);
}
