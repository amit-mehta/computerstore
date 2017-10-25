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
public class CalculateTotalServiceTest {

    @InjectMocks
    private CalculateTotalService totalService;
    @Mock
    private CatalogService catalogService;

    @Test
    public void shouldCalculateTotal() {
        Map<String, Integer> orderMap = new LinkedHashMap<>();
        orderMap.put("atv", 3);
        orderMap.put("vga", 1);
        orderMap.put("mbp",1);
        orderMap.put("ipd",4);

        given(catalogService.getCatalog()).willCallRealMethod();
//        Map<String, Catalog> catalogMap = new LinkedHashMap<>();
//        catalogMap.put("atv", new Catalog("atv", "Apple Tv", 109.50f));
//        catalogMap.put("vga", new Catalog("vga", "VGA adapter", 30.00f));
//        catalogMap.put("mbp", new Catalog("mbp", "MacBook Pro", 1399.99f));
//        catalogMap.put("ipd", new Catalog("ipd", "Super iPad", 549.99f));


        Float actualTotal = totalService.calculateTotal(orderMap);

        Float expectedTotal = (109.5f*3f)+(30.00f*1)+(1399.99f*1)+(549.99f*4);
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }
}