package com.sina.store.model.mapper;

import com.sina.store.model.dto.ProductDTO;
import com.sina.store.model.entity.Product;


public class ProductMapper {

    public static Product mapTo(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName() != null ? dto.getName() : null);
        product.setStock(dto.getStock() != null ? dto.getStock() : null);
        product.setPrice(dto.getPrice() != null ? dto.getPrice() : null);
        product.setDescription(dto.getDescription() != null ? dto.getDescription() : null);
        return product;
    }

    public static ProductDTO mapTo(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName() != null ? entity.getName() : null);
        dto.setStock(entity.getStock() != null ? entity.getStock() : null);
        dto.setPrice(entity.getPrice() != null ? entity.getPrice() : null);
        return dto;
    }

}
