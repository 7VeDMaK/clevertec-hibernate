package org.example.exception;

public class NotFoundCategoryException extends NotFoundException {
    public NotFoundCategoryException(String message) {
        super(message);
    }

    public NotFoundCategoryException(Long id) {
        super("Category id " + id + " not found");
    }
}
