package com.role_base.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.role_base.message.ResponseMessage;

@ControllerAdvice
public class ExceptionFile extends ResponseEntityExceptionHandler{

    @ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc){
    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File to large !"));
    }
}
