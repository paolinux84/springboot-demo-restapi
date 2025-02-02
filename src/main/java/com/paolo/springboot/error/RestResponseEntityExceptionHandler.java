package com.paolo.springboot.error;

import com.paolo.springboot.controller.DepartmentController;
import com.paolo.springboot.entity.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice --> Without @RequestBody (need to return ResponseEntity)
@RestControllerAdvice // --> With @RequestBody (basically serialize entity to JSON using jackson)
//@ResponseStatus --> Not clear when to use on class level???
/**
 * Guide to exception handling in Spring
 * https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 * https://reflectoring.io/spring-boot-exception-handling/
 * https://www.bezkoder.com/spring-boot-restcontrolleradvice/
 */
public class RestResponseEntityExceptionHandler  {  // If needed can extends ResponseEntityExceptionHandler that already manage various exceptions
    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDepartmentNotFoundException(DepartmentNotFoundException e, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return message;

    }

    /*
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleDepartmentNotFoundException(DepartmentNotFoundException e, WebRequest request) {

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }

    */

}
