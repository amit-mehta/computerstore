package org.sample.store;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountRule {
    private String discountOnSku;
    private int thresholdQuantity;
    private int discountQuantity;
    private int discountAmount;
}
