package com.dmitry.NewsClient.exeption;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.dmitry.NewsClient.dto.CustomResponseEntityException;
import com.dmitry.NewsClient.dto.ResponseErrorDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<CustomResponseEntityException> handleAccessDeniedException(CustomException customExeption) {
        int[] listError = {Integer.parseInt(String.valueOf(customExeption.getCode()))};
        var errorDetails = new CustomResponseEntityException();
        errorDetails.setTimestamp(LocalDateTime
                        .now()
                        .toString())
                .setStatusCode(Integer.parseInt(String.valueOf(customExeption.getCode())))
                .setCodes(listError);

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> bodyValidationExceptionHandler(MethodArgumentNotValidException ex) {
        String message = ex
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        System.err.println(message);
        List<Integer> errorCodes = Collections
                .singletonList(ErrorCodes
                        .errorCodes
                        .get(message));

        ResponseErrorDTO responseErrorDTO = ResponseErrorDTO.builder()
                .codes(errorCodes)
                .statusCode(errorCodes
                        .stream()
                        .findFirst()
                        .get())
                .timeStamp(LocalDateTime
                        .now()
                        .toString())
                .build();

        return ResponseEntity
                .badRequest()
                .body(responseErrorDTO);
    }

}

