package com.code.avaliacao.advice;

import com.code.avaliacao.dto.ApiErrorDTO;
import com.code.avaliacao.exception.DeletarProjetoException;
import com.code.avaliacao.exception.ValidaProjetoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidaProjetoException.class)
    public ResponseEntity<ApiErrorDTO> handleValidaProjetoException(HttpServletRequest request, ValidaProjetoException ex) {
        ApiErrorDTO response = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ApiErrorDTO>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeletarProjetoException.class)
    public ResponseEntity<ApiErrorDTO> handleDeletarProjetoException(HttpServletRequest request, DeletarProjetoException ex) {
        ApiErrorDTO response = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ApiErrorDTO>(response, HttpStatus.BAD_REQUEST);
    }
}
