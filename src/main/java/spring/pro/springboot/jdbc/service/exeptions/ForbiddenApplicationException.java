package spring.pro.springboot.jdbc.service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenApplicationException extends ResponseStatusException {
    private static final long serialVersionUID = 6986732753208681191L;

    public ForbiddenApplicationException() {
        super(HttpStatus.FORBIDDEN);
    }

    public ForbiddenApplicationException(String reason, Object ... args) {
        super(HttpStatus.FORBIDDEN, String.format(reason, args));
    }
}
