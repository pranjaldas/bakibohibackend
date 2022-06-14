package click.pranjalonline.bakibohibackend.main.exceptions;

import click.pranjalonline.bakibohibackend.main.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundExceptionHandler(
            ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails<String> errorDetails= new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.toString(),
                exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    // FOR CLIENT INPUT VALIDATION
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> validationErrors= new HashMap<>();
        ex.getAllErrors().forEach((error)->{
            validationErrors.put(((FieldError)error).getField(),error.getDefaultMessage());
        });
        ErrorDetails<Map<String,String>> errorDetails= new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),validationErrors);
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
