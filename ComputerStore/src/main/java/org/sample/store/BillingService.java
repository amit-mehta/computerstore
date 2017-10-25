package org.sample.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BillingService {
    private CalculateTotalService calculateTotalService;
    private CalculateDiscountService calculateDiscountService;
    private OrderReader orderReader;

    @Autowired
    public BillingService(CalculateTotalService calculateTotalService,
                          CalculateDiscountService calculateDiscountService, OrderReader orderReader) {
        this.calculateTotalService = calculateTotalService;
        this.calculateDiscountService = calculateDiscountService;
        this.orderReader = orderReader;
    }

    public Float generateDiscountedBill(String purchasedItems) {
        Map<String, Integer> orderMap = orderReader.readOrder(purchasedItems);
        Float totalAmount = calculateTotalService.calculateTotal(orderMap);
        Float totalDiscount = calculateDiscountService.calculateDiscount(orderMap);
        return totalAmount - totalDiscount;
    }
}
