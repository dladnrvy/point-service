package com.example.pointservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "barcodes")
@AllArgsConstructor
@NoArgsConstructor
public class Barcode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", length = 9, unique = true)
    private Long userId;

    @Column(nullable = false, length = 10, unique = true)
    private String barcode;
}
