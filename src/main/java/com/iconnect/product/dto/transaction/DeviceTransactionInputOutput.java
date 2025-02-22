package com.iconnect.product.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeviceTransactionInputOutput {
    private UUID siteProductId;
    private HashMap <String, Data> value;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Data {
        private String value;
        private String upperBond;
        private String lowerBond;
    }
}
