package org.example.exception;

public class NotFoundCarException extends NotFoundException {
    public NotFoundCarException(String message) {
        super(message);
    }

    public NotFoundCarException(Long id) {
        super("Car id " + id + " not found");
    }
}
