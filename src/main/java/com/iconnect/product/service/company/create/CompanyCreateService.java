package com.iconnect.product.service.company.create;

import com.iconnect.product.dto.company.create.CompanyCreateInput;

import java.util.UUID;

public interface CompanyCreateService {
    Object create (CompanyCreateInput input, UUID companyId);
}
