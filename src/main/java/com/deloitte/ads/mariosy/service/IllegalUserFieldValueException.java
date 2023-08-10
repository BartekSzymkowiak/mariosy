package com.deloitte.ads.mariosy.service;

public class IllegalUserFieldValueException extends Exception {
    public IllegalUserFieldValueException(String errorMessage) {
        super(errorMessage);
    }
}
