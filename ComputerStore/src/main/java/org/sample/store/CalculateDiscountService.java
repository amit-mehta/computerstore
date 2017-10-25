package org.sample.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalculateDiscountService {
    private CatalogService catalogService;
    private DiscountRuleEngine discountRuleEngine;

    @Autowired
    public CalculateDiscountService(CatalogService catalogService, DiscountRuleEngine discountRuleEngine) {
        this.catalogService = catalogService;
        this.discountRuleEngine = discountRuleEngine;
    }

    public Float calculateDiscount(Map<String, Integer> orderMap) {
        Map<String, Catalog> catalogMap = catalogService.getCatalog();
        Map<String, DiscountRule> discountRuleMap = discountRuleEngine.getDiscountRules();
        Float totalDiscount = 0f;
        for (Map.Entry<String, Integer> orderEntry : orderMap.entrySet()) {
            DiscountRule discountRule = discountRuleMap.get(orderEntry.getKey());
            if (discountRule != null) {
                String requiredSkuForDiscount = discountRule.getDiscountOnSku();
                Integer requiredSkuQuantity = orderMap.get(requiredSkuForDiscount);
                if (requiredSkuQuantity != null && requiredSkuQuantity >= discountRule.getThresholdQuantity()) {
                    if (discountRule.getDiscountQuantity() > 0) {
                        int discountedQty = requiredSkuQuantity / discountRule.getThresholdQuantity();
                        totalDiscount += discountedQty * catalogMap.get(orderEntry.getKey()).getPrice();
                        continue;
                    }
                    if (discountRule.getDiscountAmount() > 0) {
                        totalDiscount += discountRule.getDiscountAmount() * requiredSkuQuantity;
                    }
                }
            }
        }
        return totalDiscount;
    }
}
