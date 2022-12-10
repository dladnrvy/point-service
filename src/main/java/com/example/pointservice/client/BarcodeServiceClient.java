package com.example.pointservice.client;


import com.example.pointservice.dto.basic.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="barcode-service")
public interface BarcodeServiceClient {
    @GetMapping("/barcode/find")
    BasicResponse getBarcodeId(@RequestParam String barcode);
}
