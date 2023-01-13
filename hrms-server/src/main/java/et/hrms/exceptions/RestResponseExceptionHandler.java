package et.hrms.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = RecordNotFoundException.class)
    protected ResponseEntity<Object> handleUserRequestNotFoundException(RecordNotFoundException ex, WebRequest webRequest) {
        List<String> errorList = new ArrayList<>();
        errorList.add(ex.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage(), errorList);


        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex,
                                                   final WebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        log.error("Constraint Violation: ", ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());

    }

}
