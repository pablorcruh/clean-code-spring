package ec.com.pablorcruh.bankingtransactions.infrastructure.web.handler;

import ec.com.pablorcruh.bankingtransactions.domain.exceptions.AccountNotFoundException;
import ec.com.pablorcruh.bankingtransactions.domain.exceptions.InsufficientBalanceException;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAccountNotFound(AccountNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now(),
                List.of()
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorMessage> handleInsufficientBalance(InsufficientBalanceException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now(),
                List.of()
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationError(
            MethodArgumentNotValidException exception, HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation failed for request";
        List<String> details = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError).toList();
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                Instant.now(),
                details
        );
        return ResponseEntity.status(status).body(errorMessage);

    }

    private String formatFieldError(FieldError error){
        return "$s: $s".formatted(
                error.getField(),
                error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid Value"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericError(
            Exception exception,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage errorMessage = new ErrorMessage(
                status.value(),
                status.getReasonPhrase(),
                "Unexpected Error",
                request.getRequestURI(),
                Instant.now(),
                List.of(exception.getClass().getSimpleName())
        );
        return ResponseEntity.status(status).body(errorMessage);
    }
}
