package nl.novi.hello.exception;

import java.io.Serial;

public class BadRequestException extends Throwable {
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

}
