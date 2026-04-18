package com.vicsergeev.WalletManagerApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Created by Vic
 * 18.04.2026
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletUpdateRequest {
    private String updatedWalletTitle;
}
