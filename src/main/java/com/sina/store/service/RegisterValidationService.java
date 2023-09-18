package com.sina.store.service;

import com.sina.store.exception.*;
import com.sina.store.model.dto.RegisterCustomerDTO;
import com.sina.store.model.entity.Customer;
import com.sina.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterValidationService {

    private final CustomerRepository customerRepository;


    public void validateCustomer(RegisterCustomerDTO data) {
        if (data.getName() == null || data.getName().isEmpty()) {
            throw new CanNotBeEmpty("Name is required.");
        }
        if (data.getSurname() == null || data.getSurname().isEmpty()) {
            throw new CanNotBeEmpty("Surname is required.");
        }
        if (data.getAddress() == null || data.getAddress().isEmpty()) {
            throw new CanNotBeEmpty("Address is required.");
        }
        validateMailAddress(data.getMailAddress());
        validatePhoneNumber(data.getMobileNo());
        validateCreditCardNumber(data.getCreditCardNumber());

    }

    public void validatePhoneNumber(String mobileNo) {

        if (!StringUtils.hasText(mobileNo))
            throw new CanNotBeEmpty(mobileNo.getClass().getName());

        Optional<Customer> customerOptional = customerRepository.getByMobileNo(mobileNo);
        if (customerOptional.isPresent()) {
            throw new ExistingMobileNo(mobileNo);
        }

    }

    public void validateMailAddress(String mailAdress) {

        if (!StringUtils.hasText(mailAdress))
            throw new CanNotBeEmpty(mailAdress.getClass().getName());

        Optional<Customer> customerOptional = customerRepository.getByMailAddress(mailAdress);
        if (customerOptional.isPresent()) {
            throw new ExistingMailAddress(mailAdress);
        }

        boolean valid = EmailValidator.getInstance().isValid(mailAdress);

        boolean valid2 = EmailValidator.getInstance(true).isValid(mailAdress);

        StoreBulkException bulkException = new StoreBulkException();

        if (!valid)
            bulkException.addException(new WrongMailAddressForm());

        if (!valid2)
            bulkException.addException(new WrongMailAddressForm());

        bulkException.throwIfNecessary();

    }

    public void validateCreditCardNumber(String creditCardNumber) {

        if (!StringUtils.hasText(creditCardNumber))
            throw new CanNotBeEmpty(creditCardNumber.getClass().getName());

        Optional<Customer> customerOptional = customerRepository.getByCreditCardNumber(creditCardNumber);
        if (customerOptional.isPresent()) {
            throw new ExistingCreditCardNumber(creditCardNumber);
        }

        if (!CreditCardValidator.genericCreditCardValidator().isValid(creditCardNumber)) {
            throw new WrongCreditCardNumber(creditCardNumber);
        }
    }
}
