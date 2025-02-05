package com.iconnect.product.service.customer.edit;

import com.iconnect.product.dto.customer.edit.CustomerEditInput;
import com.iconnect.product.service.customer.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEditServiceImpl implements CustomerEditService {

    private final CustomerValidator validator;
    @Override
    public Object edit(CustomerEditInput input) {
        return null;
    }
}
