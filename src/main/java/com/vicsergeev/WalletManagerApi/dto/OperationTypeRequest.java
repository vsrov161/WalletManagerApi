package com.vicsergeev.WalletManagerApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/*
 * Created by Vic
 * 17.04.2026
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationTypeRequest {
    @NotNull
    private UUID walletId;

    @NotNull
    private OperationType operationType;

    @Min(1)
    private Long amount;
}
