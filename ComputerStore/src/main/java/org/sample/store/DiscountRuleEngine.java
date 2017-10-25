package org.sample.store;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountRuleEngine {

    public Map<String, DiscountRule> getDiscountRules() {
        Map<String, DiscountRule> discountRules = new HashMap<String, DiscountRule>();
        discountRules.put("ipd", new DiscountRule("ipd",4,0,50));
        discountRules.put("atv", new DiscountRule("atv",3,1,0));
        discountRules.put("vga", new DiscountRule("mbp",1,1,0));
        return discountRules;
    }

}
