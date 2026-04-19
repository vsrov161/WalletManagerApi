package com.vicsergeev.WalletManagerApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    @Schema(example = "1ea256d7-6ba4-4580-bbf3-0d53da820020", description = "wallet UUID")
    private String walletId;

    @NotNull
    @Schema(example = "DEPOSIT", allowableValues = {"DEPOSIT", "WITHDRAW"}, description = "financial operation type")
    private OperationType operationType;

    @Min(1)
    @Schema(example = "123", description = "amount of money for operation")
    private Long amount;
}
