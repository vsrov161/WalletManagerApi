package com.vicsergeev.WalletManagerApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Created by Vic
 * 18.04.2026
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletCreateRequest {
    @NotNull
    @Min(0)
    @Schema(example = "1234", description = "initial balance (min value = 0)")
    private Long initialBalance;

    @NotNull
    @Schema(example = "my personal wallet", description = "wallet title")
    private String walletTitle;
}
