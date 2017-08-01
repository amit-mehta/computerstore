package au.com.qantas.freight.intlcust.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiFieldError {
    private String objectName;
    private String field;
    private Object rejectedValue;
    private boolean bindingFailure;
    private String message;
}
