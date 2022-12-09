package com.example.pointservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class pointResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="point_id", nullable = false)
    private Long pointId;

    @Column(nullable = false)
    private Long point;

    @Column(name="status", nullable = false, length = 1)
    private Integer status;

    @Column(name="approvedDt", nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date approvedDt;
}
