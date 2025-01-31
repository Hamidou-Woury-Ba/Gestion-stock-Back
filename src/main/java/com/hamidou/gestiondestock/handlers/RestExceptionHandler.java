package com.hamidou.gestiondestock.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.InvalidEntityException;

/**
 * Cette classe permet de gérer les exceptions de type EntityNotFoundException et InvalidEntityException
 * Elle hérite de ResponseEntityExceptionHandler
 */

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    /**
     * Cette méthode permet de gérer les exceptions de type EntityNotFoundException 
     * @param exception : l'exception à gérer 
     * @param webRequest : la requête web
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);

    }

    /**
     * Cette méthode permet de gérer les exceptions de type InvalidEntityException 
     * @param exception : l'exception à gérer 
     * @param webRequest : la requête web
     * @return
     */

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);

    }

}
