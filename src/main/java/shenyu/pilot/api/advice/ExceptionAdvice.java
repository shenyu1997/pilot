package shenyu.pilot.api.advice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by sheyu on 5/18/2017.
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> exceptionHandler(EmptyResultDataAccessException e) {
        return ResponseEntity.notFound().build();
    }
}
