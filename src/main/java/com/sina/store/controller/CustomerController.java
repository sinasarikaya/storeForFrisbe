package com.sina.store.controller;

import com.sina.store.model.dto.GetPaymentListDTO;
import com.sina.store.model.dto.PaymentDTO;
import com.sina.store.model.dto.RegisterCustomerDTO;
import com.sina.store.model.dto.ResponseData;
import com.sina.store.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @PostMapping(REGISTER_CUSTOMER)
    @ApiOperation(value = "Muşteri Kayıt Ekranı", notes = "Customer tablosuna kayıt atması beklenir.")
    public ResponseData<String> registerCustomer(@RequestBody RegisterCustomerDTO data) {
        return customerService.registerCustomer(data);
    }

    @PostMapping(GET_PAYMENT_LIST)
    @ApiOperation(value = "Ödemelerim Sayfası", notes = "Payment tablosundaki değerlerin gelmesi beklenir.")
    public ResponseData<Page<PaymentDTO>> getPaymentsByCustomer(@RequestBody GetPaymentListDTO data, Pageable pageable) {
        return customerService.getPaymentsByCustomer(data, pageable);
    }

}
