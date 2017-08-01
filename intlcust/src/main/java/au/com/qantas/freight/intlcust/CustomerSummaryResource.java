package au.com.qantas.freight.intlcust;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class CustomerSummaryResource extends Resource<CustomerSummary> {

    public CustomerSummaryResource(CustomerSummary content, Link... links) {
        super(content, links);
    }

    public CustomerSummaryResource(CustomerSummary content, Iterable<Link> links) {
        super(content, links);
    }
}