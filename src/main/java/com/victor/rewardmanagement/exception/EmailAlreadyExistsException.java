package com.victor.rewardmanagement.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
