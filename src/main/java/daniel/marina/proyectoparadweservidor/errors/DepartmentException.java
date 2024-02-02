package daniel.marina.proyectoparadweservidor.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DepartmentException extends RuntimeException{
    public DepartmentException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class DepartmentBadRequestException extends DepartmentException {
        public DepartmentBadRequestException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class DepartmentNotFoundException extends DepartmentException {
        public DepartmentNotFoundException(String message) {
            super(message);
        }
    }
}
