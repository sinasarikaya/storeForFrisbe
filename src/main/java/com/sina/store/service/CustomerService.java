package com.sina.store.service;

import com.sina.store.model.dto.GetPaymentListDTO;
import com.sina.store.model.dto.PaymentDTO;
import com.sina.store.model.dto.RegisterCustomerDTO;
import com.sina.store.model.dto.ResponseData;
import com.sina.store.model.entity.Customer;
import com.sina.store.model.entity.Payment;
import com.sina.store.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService extends BaseService {
    private final PaymentRepository paymentRepository;
    private final RegisterValidationService registerValidationService;

    @Transactional
    public ResponseData<String> registerCustomer(RegisterCustomerDTO data) {
        try {

            registerValidationService.validateCustomer(data);

            Customer customer = new Customer();
            customer.setName(data.getName());
            customer.setSurname(data.getSurname());
            customer.setAddress(data.getAddress());
            customer.setMobileNo(data.getMobileNo());
            customer.setMailAddress(data.getMailAddress());
            customer.setCreditCardNumber(data.getCreditCardNumber());

            return new ResponseData.Builder<String>().dataWithType(SUCCESS_RESPONSE).build();
        } catch (Exception e) {
            log.error("", e);
            return new ResponseData.Builder<String>().dataWithType(FAIL_RESPONSE).build();
        }
    }

    public ResponseData<Page<PaymentDTO>> getPaymentsByCustomer(GetPaymentListDTO data, Pageable pageable) {
        return new ResponseData.Builder<Page<PaymentDTO>>().
                dataWithType(paymentRepository.findAllPaymentsByEmployeeAndDate(data.getCustomerId(), data.getBeginDate(), data.getEndDate(), pageable)
                        .map(this::getPaymentDTOWithPaymentDetails)).build();
    }

    /**
     * Kullanıcının ödeme bilgisi detaylarını setler ve getirir.
     *
     * @param payment
     * @return PaymentDTO
     */
    private PaymentDTO getPaymentDTOWithPaymentDetails(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setCustomerId(payment.getCustomer().getId());
        paymentDTO.setDeliveryAddress(payment.getDeliveryAddress());
        paymentDTO.setDateOrder(payment.getDateOrder().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        paymentDTO.setTotalPrice(payment.getTotalPrice());
        paymentDTO.setProductDTOList(paymentDTO.getProductDTOList());

        return paymentDTO;
    }

}
