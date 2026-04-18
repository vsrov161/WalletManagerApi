package com.vicsergeev.WalletManagerApi.exception;

/*
 * Created by Vic
 * 17.04.2026
 */
public class CustomInsufficientFundsException extends RuntimeException {
    public CustomInsufficientFundsException(String message) {
        super(message);
    }
}
