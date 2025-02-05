package com.iconnect.product.service.customer.create;

import com.iconnect.product.dto.customer.create.CustomerCreateInput;
import com.iconnect.product.service.customer.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreateServiceImpl implements CustomerCreateService {

    private final CustomerValidator validator;
    @Override
    public Object create(CustomerCreateInput input) {
        return null;
    }
}
