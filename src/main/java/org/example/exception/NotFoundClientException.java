package org.example.exception;

public class NotFoundClientException extends NotFoundException {
    public NotFoundClientException(String message) {
        super(message);
    }

    public NotFoundClientException(Long id) {
        super("Client id " + id + " not found");
    }
}
