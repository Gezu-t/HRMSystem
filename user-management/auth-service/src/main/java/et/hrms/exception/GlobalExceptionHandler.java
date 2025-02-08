package et.hrms.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        log.error("Authentication failed: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Authentication failed",
                ex.getMessage()
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("JWT token expired: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Token expired",
                "Please login again to get a new token"
        );
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiError> handleSignatureException(SignatureException ex) {
        log.error("JWT signature verification failed: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Invalid token",
                "Token signature verification failed"
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("Bad credentials: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Invalid credentials",
                "Username or password is incorrect"
        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        log.error("Registration failed - Username exists: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.CONFLICT,
                "USERNAME_EXISTS",
                ex.getMessage()
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        log.error("Registration failed - Email exists: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.CONFLICT,
                "EMAIL_EXISTS",
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.error("Validation failed: {}", errors);
        return createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                errors.toString()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred"
        );
    }

    private ResponseEntity<ApiError> createErrorResponse(
            HttpStatus status, String message, String details) {
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .details(details)
                .build();
        return new ResponseEntity<>(error, status);
    }
}