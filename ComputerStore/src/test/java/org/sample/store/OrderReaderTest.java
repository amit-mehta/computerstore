package org.sample.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class OrderReaderTest {

    @InjectMocks
    private OrderReader orderReader;

    @Test
    public void shouldReadOrder() {
        String orderText = "atv, ipd, ipd, atv, ipd, ipd, ipd";
        Map<String, Integer> orderMap = orderReader.readOrder(orderText);

        assertThat(orderMap.size()).isEqualTo(2);

        assertThat(orderMap.get("atv")).isEqualTo(2);
        assertThat(orderMap.get("ipd")).isEqualTo(5);
    }
}