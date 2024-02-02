package daniel.marina.proyectoparadweservidor.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class UserExceptionBadRequest extends UserException {
        public UserExceptionBadRequest(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class UserExceptionNotFound extends UserException {
        public UserExceptionNotFound(String message) {
            super(message);
        }
    }
}
