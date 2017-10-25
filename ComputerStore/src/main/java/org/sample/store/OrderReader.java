package org.sample.store;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderReader {

    public Map<String, Integer> readOrder(String orderText) {
        List<String> orders = Arrays.stream(orderText.split(",")).map(String::trim).collect(Collectors.toList());

        final Map<String, Integer> orderStorage = new HashMap<>();

        orders.forEach(item -> {
            orderStorage.merge(item, 1, (a, b) -> a + b);
        });
        return orderStorage;
    }
}
