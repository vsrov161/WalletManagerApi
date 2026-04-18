package com.vicsergeev.WalletManagerApi.dto;

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
    private UUID id;
    private Long balance;
    private String walletTitle;
}
