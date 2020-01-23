package spring.pro.springboot.jdbc.service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestApplicationException extends ResponseStatusException {
    private static final long serialVersionUID = -777412123488175668L;

    public BadRequestApplicationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestApplicationException(String reason, Object ... args) {
        super(HttpStatus.BAD_REQUEST, String.format(reason, args));
    }
}
