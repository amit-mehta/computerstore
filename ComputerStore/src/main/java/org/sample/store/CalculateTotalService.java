package org.sample.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalculateTotalService {

    private CatalogService catalogService;

    @Autowired
    public CalculateTotalService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public Float calculateTotal(Map<String, Integer> orderMap) {
        Map<String, Catalog> catalogMap = catalogService.getCatalog();
        Float total = 0f;
        for(Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            total += entry.getValue() * catalogMap.get(entry.getKey()).getPrice();
        }
        return total;
    }


}
