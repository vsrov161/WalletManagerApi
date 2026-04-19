package com.vicsergeev.WalletManagerApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/*
 * Created by Vic
 * 16.04.2026
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletDto {
    @Schema(example = "1ea256d7-6ba4-4580-bbf3-0d53da820020", description = "wallet UUID")
    private UUID id;
    @Schema(example = "123", description = "amount of money for operation")
    private Long balance;
    @Schema(example = "my personal wallet", description = "wallet title")
    private String walletTitle;
}
