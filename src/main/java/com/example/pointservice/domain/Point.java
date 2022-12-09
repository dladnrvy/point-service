package com.example.pointservice.domain;

import com.example.pointservice.exception.NotEnoughPointException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "points")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="barcode_id", nullable = false)
    private Long barcodeId;

    @Column(name="category_id", nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long point;



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
        this.point = restPoint;
    }

    @Builder
    public Point(Long id, Long barcodeId, Long categoryId, Long point) {
        this.id = id;
        this.barcodeId = barcodeId;
        this.categoryId = categoryId;
        this.point = point;
    }
}
