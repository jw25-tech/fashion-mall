package com.fashion.mall.entity;

import com.fashion.mall.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Lob
    @Column(length=500)
    private String description;  // 제품 설명

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private LocalDateTime regTime;  // 등록 시간

    private LocalDateTime updateTime; // 수정 시간
}
