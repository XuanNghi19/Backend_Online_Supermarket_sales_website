package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.OrderDetail;
import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class OrderDetailResponse {
    @JsonProperty("product")
    private ProductResponse product;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail, ProductResponse productResponse){
        return OrderDetailResponse.builder()
            .product(productResponse)
            .numberOfProducts(orderDetail.getNumberOfProducts())
            .build();
    }
}
