package au.com.qantas.freight.intlcust;

import au.com.qantas.freight.intlcust.common.ResourceNotFoundException;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CustomerSummaryAssembler extends ResourceAssemblerSupport<CustomerSummary, CustomerSummaryResource> {

    public CustomerSummaryAssembler() {
        super(CustomerSummaryController.class, CustomerSummaryResource.class);
    }

    @Override
    public CustomerSummaryResource toResource(CustomerSummary customerSummary) {
        if(customerSummary == null) {
            throw new ResourceNotFoundException();
        }
        CustomerSummaryResource resource = new CustomerSummaryResource(customerSummary);

//        resource.add(linkTo(methodOn(CustomerSummaryController.class)).withSelfRel());

        return resource;
    }
}
