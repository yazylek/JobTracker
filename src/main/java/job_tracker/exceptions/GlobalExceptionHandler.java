package job_tracker.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex
    ){
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        String message = "Validation failed";
        if (!errors.isEmpty()){
            FieldError fieldError = errors.get(0);

            message = fieldError.getField()
                    + ": "
                    + fieldError.getDefaultMessage();
        }

        ErrorResponse error = new ErrorResponse(400, message);

        return ResponseEntity.badRequest().body(error);
    }
}
