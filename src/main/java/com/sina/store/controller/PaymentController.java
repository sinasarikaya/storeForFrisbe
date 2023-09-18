package com.sina.store.controller;

import com.sina.store.model.dto.PaymentDTO;
import com.sina.store.model.dto.ResponseData;
import com.sina.store.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController extends BaseController {

    private final PaymentService paymentService;

    @PostMapping(SAVE_PAYMENT)
    @ApiOperation(value = "Ödeme Ekran", notes = "Payment ve PaymentProduct tablolarına kayıt atması beklenir.")
    public ResponseData<PaymentDTO> savePayment(@RequestBody PaymentDTO data) {
        return paymentService.savePayment(data);
    }

}
