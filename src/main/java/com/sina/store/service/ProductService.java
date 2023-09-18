package com.sina.store.service;

import com.sina.store.exception.NotEnoughProductStockException;
import com.sina.store.model.entity.Product;
import com.sina.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService extends BaseService {


    private final ProductRepository productRepository;

    /**
     * Üründen stok düşürme.
     *
     * @param product
     * @param productCount
     */
    @Transactional
    public void minusStock(Product product, BigDecimal productCount) {

        if (product.getStock().subtract(productCount).compareTo(BigDecimal.valueOf(0L)) > 0) {
            try {
                product.setStock(product.getStock().subtract(productCount));
                productRepository.save(product);
            } catch (Exception e) {
                log.error("", e);
            }
        } else {
            throw new NotEnoughProductStockException(product.getName());
        }
    }

}
