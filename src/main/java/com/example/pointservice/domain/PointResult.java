package com.example.pointservice.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PointResult {

    /** 결과 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="result_id", nullable = false)
    private Long id;

    /** 포인트 id */
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="points_id", nullable = false)
    private Point pointId;

    /** 상점 id */
    @Column(name="partner_id", nullable = false)
    private Long partnerId;

    /** 포인트 */
    @Column(nullable = false)
    private Long resultPoint;

    /** 상태 */
    @Column(name="status", nullable = false, length = 1)
    private Integer status;

    /** 저장일 */
    @CreatedDate
    @Column(name="approved_dt")
    private LocalDateTime approvedDt;

    @Builder
    public PointResult(Long id, Point pointId, Long partnerId, Long resultPoint, Integer status, LocalDateTime approvedDt) {
        this.id = id;
        this.pointId = pointId;
        this.partnerId = partnerId;
        this.resultPoint = resultPoint;
        this.status = status;
        this.approvedDt = approvedDt;
    }
}
