package au.com.qantas.freight.intlcust.common;

import au.com.qantas.freight.intlcust.common.ApiError;
import au.com.qantas.freight.intlcust.common.ApiFieldError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final Set<ApiFieldError> apiFieldErrors = new HashSet<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            final ApiFieldError apiFieldError = ApiFieldError.builder()
                    .objectName(fieldError.getObjectName())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue())
                    .message(fieldError.getDefaultMessage())
                    .bindingFailure(fieldError.isBindingFailure())
                    .build();
            apiFieldErrors.add(apiFieldError);
        }

        //final HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //logger.warn(formatMessage(exception, error, request), exception);
        return ApiError.builder()
                .message("Validation failed. Error count: " + apiFieldErrors.size())
                .fieldErrors(apiFieldErrors)
                .build();
    }

}
