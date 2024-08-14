package com.banyuijo.foundation.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductCreateRequest {

    private String siteProductName;

    private String siteProductCode;

    private UUID productTypeId;

}
