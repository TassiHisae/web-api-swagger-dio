package dio.mywebapi.exception;

import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseError responseError(String message, HttpStatus statusCode){
        return new ResponseError()
                .setStatus("error")
                .setError(message)
                .setStatusCode(statusCode.value());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception ex, WebRequest webRequest){
        if(ex.getClass().isAssignableFrom(UndeclaredThrowableException.class)){
            UndeclaredThrowableException exception = (UndeclaredThrowableException) ex;
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), webRequest);
        }else {
            String message = messageSource.getMessage("error.server", new Object[]{ex.getMessage()}, null);
            ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(ex, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);

        }
    }

    private ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest webRequest) {
        ResponseError error = responseError(ex.getMessage(), HttpStatus.CONFLICT);
        return handleExceptionInternal(ex, error, headers(), HttpStatus.CONFLICT, webRequest);
    }
}
