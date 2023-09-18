package com.sina.store.model.mapper;

import com.sina.store.model.dto.PaymentDTO;
import com.sina.store.model.entity.Payment;

import java.time.format.DateTimeFormatter;

public class PaymentMapper {

    public static PaymentDTO mapTo(Payment entity) {
        if (entity == null) {
            return null;
        }
        PaymentDTO dto = new PaymentDTO();

        dto.setId(entity.getId());
        dto.setDateOrder(entity.getDateOrder().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        dto.setDeliveryAddress(entity.getDeliveryAddress());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setProductDTOList(PaymentProductMapper.mapToDto(entity.getPaymentProductList()));
        return dto;
    }

}
