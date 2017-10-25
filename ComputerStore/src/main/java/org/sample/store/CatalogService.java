package org.sample.store;

import java.util.LinkedHashMap;
import java.util.Map;

public class CatalogService {

    public Map<String, Catalog> getCatalog() {
        Map<String, Catalog> sampleCatalog = new LinkedHashMap<>();
        sampleCatalog.put("ipd",new Catalog("ipd","Super iPad", 549.99f));
        sampleCatalog.put("mbp",new Catalog("mbp","MacBook Pro", 1399.99f));
        sampleCatalog.put("atv",new Catalog("atv","Apple TV", 109.50f));
        sampleCatalog.put("vga",new Catalog("vga","VGA adapter", 30.00f));
        return sampleCatalog;
    }
}
