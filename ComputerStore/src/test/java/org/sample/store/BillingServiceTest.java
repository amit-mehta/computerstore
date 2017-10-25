package org.sample.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceTest {

    @InjectMocks
    private BillingService billingService;
    @Mock
    private CalculateTotalService calculateTotalService;
    @Mock
    private CalculateDiscountService calculateDiscountService;
    @Mock
    private OrderReader orderReader;

    @Mock
    private Map<String, Integer> orderMap;

    @Test
    public void testShouldGenerateDiscountedBill() {
        String testOrderItems = "atv, atv, atv, vga";

        given(orderReader.readOrder(testOrderItems)).willReturn(orderMap);
        given(calculateTotalService.calculateTotal(orderMap)).willReturn(1000f);
        given(calculateDiscountService.calculateDiscount(orderMap)).willReturn(200f);

        Float discountedTotalBill = billingService.generateDiscountedBill(testOrderItems);

        assertThat(discountedTotalBill).isEqualTo(800f);

    }


}