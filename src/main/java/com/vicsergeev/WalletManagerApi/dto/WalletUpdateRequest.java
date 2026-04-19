package com.vicsergeev.WalletManagerApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Schema(example = "updated wallet name", description = "new title value")
    private String updatedWalletTitle;
}
