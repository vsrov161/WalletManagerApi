package com.vicsergeev.WalletManagerApi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vicsergeev.WalletManagerApi.exception.CustomValidateException;
import jakarta.validation.constraints.NotNull;

/*
 * Created by Vic
 * 16.04.2026
 */
@NotNull
public enum OperationType {
    DEPOSIT,
    WITHDRAW;

    @JsonCreator
    public static OperationType fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new CustomValidateException("operationType cannot be empty");
        }
        try {
            return OperationType.valueOf(value.toUpperCase());
        }  catch (IllegalArgumentException e) {
            throw new CustomValidateException("invalid operation type");
        }
    }
}
