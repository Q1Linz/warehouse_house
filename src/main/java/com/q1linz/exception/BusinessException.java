package com.q1linz.exception;

/**
 * 用户操作不当导致的异常
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}
