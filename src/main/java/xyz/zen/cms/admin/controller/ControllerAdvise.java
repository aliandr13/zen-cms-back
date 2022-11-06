package xyz.zen.cms.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xyz.zen.cms.admin.controller.api.model.ErrorResponse;
import xyz.zen.cms.admin.exception.NotFoundException;
import xyz.zen.cms.admin.exception.ValidationException;

@Slf4j
@ControllerAdvice(basePackages = "xyz.zen.cms.admin.controller.api")
public class ControllerAdvise extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        return processException(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex, WebRequest request) {
        return processException(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> processException(RuntimeException ex, WebRequest request, HttpStatus status) {
        log.error("Exception: " + ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }
}
