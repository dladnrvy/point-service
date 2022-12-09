package com.example.pointservice.repository;

import com.example.pointservice.domain.Barcode;
import com.example.pointservice.dto.BarcodeCreateResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
    List<Barcode> findByUserId(Long id);
}
