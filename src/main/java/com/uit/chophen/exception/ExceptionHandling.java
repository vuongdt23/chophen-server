package com.uit.chophen.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;
import java.util.Objects;

import javax.persistence.NoResultException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.uit.chophen.utils.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String ACCOUNT_LOCKED = "Account is locked";
	private static final String METHOD_NOT_ALLOWEED = "This method is not allowed, please send a '%s' request";
	private static final String INTERNAL_SERVER_ERROR = "An error occured";
	private static final String INCORRECT_CREDENTIALS = "Your username or password is incorrect";
	private static final String ACCOUNT_DISABLED = "Account is disabled";
	private static final String ERROR_PROCESSING_FILE = "Error occured while processing file";
	private static final String NOT_ENOUGH_PERMISSION = "Insufficient permissions";
	public static final String ERROR_PATH = "/error";

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HttpResponse> badCredentialsException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HttpResponse> accessDeniedException() {
		return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(LockedException.class)
	public ResponseEntity<HttpResponse> lockedException() {
		return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
		return createHttpResponse(UNAUTHORIZED, exception.getMessage());
	}

	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<HttpResponse> emailExistException(EmailExistsException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(AccountExistsException.class)
	public ResponseEntity<HttpResponse> usernameExistException(AccountExistsException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<HttpResponse> accountDisabledException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
	}

	 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
	        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
	        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_NOT_ALLOWEED, supportedMethod));
	    }
 
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
	        LOGGER.error(exception.getMessage());
	        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
	    }

	 @ExceptionHandler(NoResultException.class)
	    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
	        LOGGER.error(exception.getMessage());
	        return createHttpResponse(NOT_FOUND, exception.getMessage());
	    }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
      return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
   }
	 
	 @RequestMapping(ERROR_PATH)
	    public ResponseEntity<HttpResponse> notFound404() {
	        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
	    }

	

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message);
		return new ResponseEntity<HttpResponse>(httpResponse, httpStatus);

	}

}
