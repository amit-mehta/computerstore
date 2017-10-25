package org.sample.store;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Catalog {
    private String sku;
    private String name;
    private Float price;
}
