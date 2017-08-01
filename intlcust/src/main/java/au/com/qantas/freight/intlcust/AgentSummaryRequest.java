package au.com.qantas.freight.intlcust;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ApiModel
public class AgentSummaryRequest {
    @ApiModelProperty(required = true)
    @NotEmpty(message = "countryCode cannot be empty")
    @Size(max = 3,min = 3, message = "countryCode has to be 3 character long")
    private String countryCode;

    @ApiModelProperty(required = true)
    @NotEmpty(message = "agentCode cannot be empty")
    @Size(max = 4,min = 4, message = "agentCode has to be 4 character long")
    private String agentCode;

    @ApiModelProperty
    @Size(max = 4, min = 4,message = "cassCode has to be 4 character long")
    private String cassCode;
}
