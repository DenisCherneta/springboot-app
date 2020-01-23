package spring.pro.springboot.jdbc.service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalApplicationException extends ResponseStatusException {
    private static final long serialVersionUID = -777412123488175668L;

    public InternalApplicationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public InternalApplicationException(String reason, Object ... args) {
        super(HttpStatus.BAD_REQUEST, String.format(reason, args));
    }
}
