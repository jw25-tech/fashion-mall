package com.fashion.mall.repository;

import com.fashion.mall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);

    List<Product> findByProductNameOrDescription(String productName, String description);  // Or조건 처리하기 -> 메서드명을 줄일 수 있는 방법 찾기

    List<Product> findByPriceLessThan(Integer price); // price보다 값이 작은 상품 조회

    @Query("select p from Product p where p.description like %:description% order by p.price desc")
    List<Product> findByDescription(@Param("description") String description);

    @Query(value="select * from products p where p.description like %:description% order by p.price desc", nativeQuery = true)
    List<Product> findByDescriptionByNative(@Param("description") String description);
}
