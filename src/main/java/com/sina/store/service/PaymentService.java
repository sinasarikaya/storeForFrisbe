package com.sina.store.service;

import com.sina.store.exception.CanNotBeEmpty;
import com.sina.store.exception.CustomerNotFoundException;
import com.sina.store.exception.InvalidPaymentAmountException;
import com.sina.store.exception.WrongCreditCardNumber;
import com.sina.store.model.dto.PaymentDTO;
import com.sina.store.model.dto.PaymentProductDTO;
import com.sina.store.model.dto.ResponseData;
import com.sina.store.model.entity.Customer;
import com.sina.store.model.entity.Payment;
import com.sina.store.model.entity.PaymentProduct;
import com.sina.store.model.mapper.PaymentMapper;
import com.sina.store.repository.CustomerRepository;
import com.sina.store.repository.PaymentProductRepository;
import com.sina.store.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProductRepository paymentProductRepository;
    private final ProductService productService;

    @Transactional
    public ResponseData<PaymentDTO> savePayment(PaymentDTO data) {

        Optional<Customer> customerOptional = customerRepository.findById(data.getCustomerId().toString());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(data.getCustomerId().toString());
        }

        checkPayment(data, customerOptional.get());

        Payment payment = new Payment();
        payment.setCustomer(customerOptional.get());
        payment.setTotalPrice(data.getTotalPrice());
        payment.setDateOrder(OffsetDateTime.now());
        payment.setDeliveryAddress(data.getDeliveryAddress());
        Payment finalPayment = paymentRepository.save(payment);

        for (PaymentProductDTO paymentProductDTO : data.getProductDTOList()) {
            PaymentProduct paymentProduct = new PaymentProduct();
            paymentProduct.setPayment(finalPayment);
            paymentProduct.setProduct(paymentProduct.getProduct());
            paymentProduct.setPrice(paymentProductDTO.getProductDTO().getPrice());
            paymentProduct.setProductCount(paymentProduct.getProductCount());
            paymentProductRepository.save(paymentProduct);
            //Stok düşürülüyor
            productService.minusStock(paymentProduct.getProduct(), BigDecimal.valueOf(paymentProduct.getProductCount()));
        }

        return new ResponseData.Builder<PaymentDTO>().dataWithType(PaymentMapper.mapTo(payment)).build();

    }

    private void checkPayment(PaymentDTO data, Customer customer) {

        if (!StringUtils.hasText(customer.getCreditCardNumber()))
            throw new CanNotBeEmpty(customer.getCreditCardNumber().getClass().getName());


        if (!CreditCardValidator.genericCreditCardValidator().isValid(customer.getCreditCardNumber())) {
            throw new WrongCreditCardNumber(customer.getCreditCardNumber());
        }

        if (data.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentAmountException();
        }
    }

}
