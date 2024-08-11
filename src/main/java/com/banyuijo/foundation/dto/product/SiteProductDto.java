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
public class SiteProductDto {
    private UUID siteProductId;
    private double currentTds;
    private double averageTds;
    private double pH;
    SiteProductStatus siteProductStatus;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class SiteProductStatus{
        private boolean waterPump;
        private boolean tdsPump;
        private boolean nutritionPump;
        private boolean mixingPump;
    }
}
