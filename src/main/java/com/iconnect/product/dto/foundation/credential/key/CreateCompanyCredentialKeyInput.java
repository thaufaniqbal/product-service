package com.iconnect.product.dto.foundation.credential.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCompanyCredentialKeyInput {
    private UUID companyId;
    private int keyTotal;
}
