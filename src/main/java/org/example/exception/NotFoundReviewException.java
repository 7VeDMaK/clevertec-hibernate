package org.example.exception;

public class NotFoundReviewException extends RuntimeException {
    public NotFoundReviewException(String message) {
        super(message);
    }

    public NotFoundReviewException(Long id) {
        super("Review id " + id + " not found");
    }
}
