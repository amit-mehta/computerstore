package au.com.qantas.freight.intlcust;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "international_customer_summary")
@Data
@ApiModel
@NoArgsConstructor
public class CustomerSummary {
    @Id
    private String customerCode;
    @JsonIgnore
    private String accountNumber;
    @ApiModelProperty
    private String customerName;
    @ApiModelProperty
    private String shopNumber;
    @ApiModelProperty
    private String streetNumber;
    @ApiModelProperty
    private String streetName;
    @ApiModelProperty
    private String streetType;
    @ApiModelProperty
    private String city;
    @ApiModelProperty
    private String state;
    @ApiModelProperty
    private String postCode;
    @ApiModelProperty
    private String countryCode;
}
