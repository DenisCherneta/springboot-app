package spring.pro.springboot.jdbc.service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundApplicationException extends ResponseStatusException {
    private static final long serialVersionUID = 7704205864947286223L;

    public NotFoundApplicationException() {
        super(HttpStatus.NOT_FOUND);
    }

    public NotFoundApplicationException(String reason, Object ... args) {
        super(HttpStatus.NOT_FOUND, String.format(reason, args));
    }
}
