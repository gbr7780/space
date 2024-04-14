package com.space.exception;

public class OutOfStockException  extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }
}
