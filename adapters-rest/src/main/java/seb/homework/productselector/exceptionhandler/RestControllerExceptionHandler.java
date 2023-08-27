package seb.homework.productselector.exceptionhandler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import seb.homework.productselector.validation.DomainException;
import seb.homework.productselector.exception.FieldErrorResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class RestControllerExceptionHandler {

   @ExceptionHandler
   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public Map<String, List<FieldErrorResponse>> handleMethodArgumentNotValid(
         MethodArgumentNotValidException exception) {
      return error(exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
            .toList());
   }

   private Map<String, List<FieldErrorResponse>> error(List<FieldErrorResponse> errors) {
      return Collections.singletonMap("errors", errors);
   }

   @ExceptionHandler(value = RuntimeException.class)
   public HttpEntity<String> handleException(RuntimeException exception) {
      if (exception instanceof DomainException domainValidationException) {
         return new ResponseEntity<>(
               domainValidationException.getPropertyName() + " : " + exception.getMessage(),
               HttpStatus.BAD_REQUEST);
      }
      throw exception;
   }
}
