package com.iconnect.product.service.customer.create;

import com.iconnect.product.dto.customer.create.CustomerCreateInput;

import java.util.UUID;

public interface CustomerCreateService {
    Object create (CustomerCreateInput input, UUID customerId);
}
