package org.sample.store;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public class BillingServiceIntegrationTest {

    @Autowired
    private BillingService billingService;

    @Test
    public void testCaseOne() {
        Float total = billingService.generateDiscountedBill("atv, atv, atv, vga");
        System.out.println("total = " + total);
        assertThat(total).isEqualTo(249.00f);
    }

    @Test
    public void testCaseTwo() {
        Float total = billingService.generateDiscountedBill("atv, ipd, ipd, atv, ipd, ipd, ipd");
        System.out.println("total = " + total);
        assertThat(total).isEqualTo(2718.95f);
    }

    @Test
    public void testCaseThree() {
        Float total = billingService.generateDiscountedBill("mbp, vga, ipd");
        System.out.println("total = " + total);
        assertThat(total).isEqualTo(1949.98f);
    }
}
