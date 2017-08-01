package au.com.qantas.freight.intlcust.common;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class ApiError {
    private String message;
    private Set<ApiFieldError> fieldErrors;
}
