package com.iconnect.product.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeviceTransactionInputOutput {
    private UUID siteProductId;
    private List<Data> data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Data {
        private String settingCode;
        private DataValue dataValue;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class DataValue {
            private String value;
            private String upperBond;
            private String lowerBond;
        }
    }

}
