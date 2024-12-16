package org.example.exception;

public class NotFoundCarShowroomException extends RuntimeException {
    public NotFoundCarShowroomException(String message) {
        super(message);
    }

    public NotFoundCarShowroomException(Long id) {
        super("Showroom id " + id + " not found");
    }
}
