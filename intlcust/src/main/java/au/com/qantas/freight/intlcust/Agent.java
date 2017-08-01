package au.com.qantas.freight.intlcust;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "agent")
@Data
@ApiModel
@NoArgsConstructor
public class Agent {
    @Id
    String iataCode;
    String customerCode;
}
