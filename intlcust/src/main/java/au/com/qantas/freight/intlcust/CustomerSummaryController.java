package au.com.qantas.freight.intlcust;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping
public class CustomerSummaryController {

    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    private final CustomerSummaryService service;
    private final CustomerSummaryAssembler assembler;

    public CustomerSummaryController(CustomerSummaryService service, CustomerSummaryAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @ApiOperation("Validate countryCode,agentCode,cassCode by fetching customer's summary")
    @PostMapping(value = "/internationalcustomer/validate/agent", produces = HAL_MEDIA_TYPE)
    @ResponseStatus(OK)
    public CustomerSummaryResource validateAgent(@Valid @RequestBody AgentSummaryRequest summaryRequest) {
        CustomerSummary customerSummary = service.getCustomerSummary(summaryRequest.getCountryCode(),
                summaryRequest.getAgentCode(), summaryRequest.getCassCode());
        return assembler.toResource(customerSummary);
    }



}
