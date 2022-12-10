package com.example.pointservice.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointResult {

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "point_id")
    private Point points;

    @Builder
    public PointResult(Long id, Long pointId, Long point, Integer status, Date approvedDt) {
        this.id = id;
        this.pointId = pointId;
        this.point = point;
        this.status = status;
        this.approvedDt = approvedDt;
    }
}
