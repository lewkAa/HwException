package ru.netology.domain;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String s) {
        super(s);
    }
}
