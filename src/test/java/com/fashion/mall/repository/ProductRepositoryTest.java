package com.fashion.mall.repository;

import com.fashion.mall.constant.ProductStatus;
import com.fashion.mall.entity.Product;
import com.fashion.mall.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ProductRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductsTest(){
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setDescription("테스트 상품 설명");
        product.setProductStatus(ProductStatus.SELL);
        product.setQuantity(50);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        Product savedProducts = productRepository.save(product);
        System.out.println(savedProducts.toString());
    }

    public void createProductList(){
        for(int i=1; i<=10; i++){
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setDescription("테스트 상품설명" + i);
            product.setProductStatus(ProductStatus.SELL);
            product.setQuantity(50);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            Product savedProducts = productRepository.save(product);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByProductNameTest(){
        this.createProductList();
        List<Product> productList = productRepository.findByProductName("테스트 상품1");
        for(Product product:productList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByProductNameOrDescriptionTest(){
        this.createProductList();
        List<Product> productList = productRepository.findByProductNameOrDescription("테스트 상품1", "테스트 상품설명4");
            for(Product product:productList){
                System.out.println(product.toString());
            }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")  // OrderBy로 정렬도 가능 Asc, Desc
    public void findByPriceLessThanTest(){
        this.createProductList();
        List<Product> productList = productRepository.findByPriceLessThan(10007);
        for(Product product:productList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByDescriptionTest(){
        this.createProductList();
        List<Product> productList = productRepository.findByDescription("테스트 상품설명");
        for(Product product:productList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByDescriptionByNative(){
        this.createProductList();
        List<Product> productList = productRepository.findByDescriptionByNative("테스트 상품설명");
        for (Product product:productList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest(){
        this.createProductList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;
        JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
                .where(qProduct.productStatus.eq(ProductStatus.SELL))
                .where(qProduct.description.like("%" + "테스트 상품설명" + "%"))
                .orderBy(qProduct.price.desc());

        List<Product> productList = query.fetch();

        for(Product product:productList){
            System.out.println(product.toString());
        }
    }
}