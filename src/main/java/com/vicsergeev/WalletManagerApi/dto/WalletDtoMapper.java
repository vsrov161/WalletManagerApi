package com.vicsergeev.WalletManagerApi.dto;

import com.vicsergeev.WalletManagerApi.entity.Wallet;

/*
 * Created by Vic
 * 16.04.2026
 */
public class WalletDtoMapper {
    public static WalletDto mapToDto(Wallet wallet) {
        if (wallet == null) return null;

        return new WalletDto(
          wallet.getId(),
          wallet.getBalance()
        );
    }

    public static Wallet mapToEntity(WalletDto walletDto) {
        if (walletDto == null) return null;

        Wallet wallet = new Wallet();
        wallet.setId(walletDto.getId());

        if (walletDto.getBalance() != null) {
            wallet.setBalance(walletDto.getBalance());
        }
        return wallet;
    }
}
