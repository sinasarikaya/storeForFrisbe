package com.sina.store.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class GetPaymentListDTO {

    Long customerId;
    OffsetDateTime beginDate;
    OffsetDateTime endDate;

}
