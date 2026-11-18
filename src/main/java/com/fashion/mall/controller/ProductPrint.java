package com.fashion.mall.controller;

import com.fashion.mall.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value="/productData")
public class ProductPrint {

    @GetMapping(value = "/printData")
    public String printProduct(Model model){
        ProductDto productDto = new ProductDto();
        productDto.setDescription("상품설명");
        productDto.setProductName("테스트 상품1");
        productDto.setPrice(10000);
        productDto.setRegTime(LocalDateTime.now());

        model.addAttribute("productDto",productDto);
        return "productData";
    }
}
