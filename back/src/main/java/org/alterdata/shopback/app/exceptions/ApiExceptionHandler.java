package org.alterdata.shopback.app.exceptions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			List<ErrorObject> errors = getErrors(ex);
			ErroResposta erroResposta = getErroResposta(ex, status, errors);
			return new ResponseEntity<>(erroResposta, status);
		}
		
		private ErroResposta getErroResposta(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
			return new ErroResposta("Requisição possui campos inválidos", status.value(), 
					status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
		}
		
		private List<ErrorObject> getErrors(MethodArgumentNotValidException ex){
			return ex.getBindingResult().getFieldErrors().stream()
					.map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
					.collect(Collectors.toList());
		}
		
		@ExceptionHandler(NomeException.class)
	    public ResponseEntity<?> handlerNomeException(NomeException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
	                ex.getClass().getName(), new Date().getTime());

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
		
		@ExceptionHandler(IdException.class)
	    public ResponseEntity<?> handlerIdException(IdException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
	                ex.getClass().getName(), new Date().getTime());

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
		
		@ExceptionHandler(EmailException.class)
	    public ResponseEntity<?> handlerIEmailxception(EmailException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
	                ex.getClass().getName(), new Date().getTime());

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
}
