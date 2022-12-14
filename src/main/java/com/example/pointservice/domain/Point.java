package com.example.pointservice.domain;

import com.example.pointservice.exception.NotEnoughPointException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "points")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point implements Serializable {

    /** 포인트 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="points_id")
    private Long id;

    /** 바코드 아이디 */
    @Column(name="barcode_id", nullable = false)
    private Long barcodeId;

    /** 카테고리 아이디 */
    @Column(name="category_id", nullable = false)
    private Long categoryId;

    /** 포인트 */
    @Column(nullable = false)
    private Long point;

    /** PointResult 조인 */
    @OneToMany(fetch = LAZY, targetEntity = Point.class)
    private List<PointResult> pointResults = new ArrayList<>();

    /**
     * point 증가
     * @param reqPoint
     */
    public void savePoint(Long reqPoint){
        this.point += reqPoint;
    }

    /**
     * point 감소
     * @param reqPoint
     */
    public void usePoint(Long reqPoint){
        Long restPoint = this.point - reqPoint;
        if(restPoint < 0){
            throw new NotEnoughPointException("need more point");
        }
        this.point -= reqPoint;
    }

    @Builder
    public Point(Long id, Long barcodeId, Long categoryId, Long point) {
        this.id = id;
        this.barcodeId = barcodeId;
        this.categoryId = categoryId;
        this.point = point;
    }
}
