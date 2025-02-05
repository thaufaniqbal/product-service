package com.iconnect.product.service.customer.delete;

import com.iconnect.product.dto.customer.delete.CustomerDeleteInput;
import com.iconnect.product.service.customer.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDeleteServiceImpl implements CustomerDeleteService {

    private final CustomerValidator validator;
    @Override
    public Object delete(CustomerDeleteInput input) {
        return null;
    }
}
