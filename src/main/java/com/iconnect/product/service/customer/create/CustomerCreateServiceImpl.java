package com.iconnect.product.service.customer.create;

import com.iconnect.product.dto.customer.create.CustomerCreateInput;
import com.iconnect.product.entity.customer.Customer;
import com.iconnect.product.repository.customer.CustomerRepository;
import com.iconnect.product.service.customer.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerCreateServiceImpl implements CustomerCreateService {
    private final CustomerRepository customerRepository;
    private final CustomerValidator validator;
    @Override
    public Object create(CustomerCreateInput input, UUID customerId) {
        Customer customer = build(input, customerId);
        customerRepository.save(customer);
        return customer;
    }
    private Customer build (CustomerCreateInput input, UUID customerId){
        Customer result = new Customer();
        result.setCustomerId(Objects.isNull(customerId) ? UUID.randomUUID() : customerId);
        result.setCustomerName(input.getCustomerName());
        result.setCreatedDate(LocalDateTime.now());
        result.setCreatedBy("system v1.0.0");
        return result;
    }
}
