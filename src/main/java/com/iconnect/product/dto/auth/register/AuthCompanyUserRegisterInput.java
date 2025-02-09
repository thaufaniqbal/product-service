package com.iconnect.product.dto.auth.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthCompanyUserRegisterInput {
    private String username;
    private UUID companyId;
}
