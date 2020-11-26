package home.train.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionHandle extends RuntimeException {

    public ExceptionHandle() {
        super();
    }

    public ExceptionHandle(String message) {
        super(message);
    }

    public ExceptionHandle(String message, Throwable cause) {
        super(message, cause);
    }
}
