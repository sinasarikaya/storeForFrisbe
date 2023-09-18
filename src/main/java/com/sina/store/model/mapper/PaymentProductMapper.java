package com.sina.store.model.mapper;

import com.sina.store.model.dto.PaymentProductDTO;
import com.sina.store.model.entity.PaymentProduct;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentProductMapper {

    public static PaymentProductDTO mapTo(PaymentProduct entity) {
        if (entity == null) {
            return null;
        }
        PaymentProductDTO dto = new PaymentProductDTO();

        dto.setId(entity.getId());
        dto.setProductDTO(entity.getProduct() != null ? ProductMapper.mapTo(entity.getProduct()) : null);
        dto.setProductCount(entity.getProductCount());

        return dto;
    }

    public static List<PaymentProductDTO> mapToDto(List<PaymentProduct> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(PaymentProductMapper::mapTo).collect(Collectors.toList());
    }

}
