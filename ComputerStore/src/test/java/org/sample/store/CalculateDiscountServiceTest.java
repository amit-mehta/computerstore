package org.sample.store;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CalculateDiscountServiceTest {

    @InjectMocks
    private CalculateDiscountService discountService;
    @Mock
    private CatalogService catalogService;
    @Mock
    private DiscountRuleEngine discountRuleEngine;

    @Test
    public void testCalculateDiscountService() {

        Map<String, Integer> orderMap = new LinkedHashMap<>();
        orderMap.put("atv", 3);
        orderMap.put("vga", 1);
        orderMap.put("mbp",1);
        orderMap.put("ipd",4);

        given(catalogService.getCatalog()).willCallRealMethod();
        given(discountRuleEngine.getDiscountRules()).willCallRealMethod();

        Float totalDiscount = discountService.calculateDiscount(orderMap);

        // atv discount = 109.5
        // vga discount = 30.0
        // ipd discount = 50*4 = 200

        assertThat(totalDiscount).isEqualTo(109.5f+30f+200f);
    }

}