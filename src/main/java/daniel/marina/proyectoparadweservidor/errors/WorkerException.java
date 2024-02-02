package daniel.marina.proyectoparadweservidor.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WorkerException extends RuntimeException{
    public WorkerException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class WorkerBadRequestException extends WorkerException {
        public WorkerBadRequestException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class WorkerNotFoundException extends WorkerException {
        public WorkerNotFoundException(String message) {
            super(message);
        }
    }
}
