package com.endava.tarapana.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.endava.tarapana.rest.exception.ErrorInfo;

@ControllerAdvice
public class ExceptionMapper {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	ErrorInfo handleBadRequest(Exception ex) {
		logger.error(ex.getLocalizedMessage() + ".", ex);
		return new ErrorInfo(ex.getLocalizedMessage());
	}

}
