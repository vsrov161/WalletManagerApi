package com.vicsergeev.WalletManagerApi.controller;

/*
 * Created by Vic
 * 17.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.OperationTypeRequest;
import com.vicsergeev.WalletManagerApi.dto.WalletDto;
import com.vicsergeev.WalletManagerApi.dto.WalletCreateRequest;
import com.vicsergeev.WalletManagerApi.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Wallet management", description = "API for manage wallets")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    @Operation(summary = "Create wallet", description = "Use to create new wallet with random ID, set initialBalance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response that wallet created successfully"),
            @ApiResponse(responseCode = "400", description = "Response when provided negative value of initial balance, or empty json")
    })
    public WalletDto create(@RequestBody @Valid WalletCreateRequest request) {
        return walletService.create(request);
    }

    @GetMapping("/wallets")
    @Operation(summary = "Show wallets", description = "Use to check the list of all existing wallets")
    @ApiResponse(responseCode = "200", description = "Response in successful loading list, initially list is empty")
    public List<WalletDto> getAll() {
        return walletService.findAll();
    }

    @GetMapping("/wallets/{id}")
    @Operation(summary = "Show wallet by ID", description = "Use to get all information about specific wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response when wallet information loaded successfully"),
            @ApiResponse(responseCode = "404", description = "Response if no such wallet in DB")
    })
    public WalletDto getWalletInfoById(@PathVariable UUID id) {
        return walletService.findById(id);
    }

    @DeleteMapping("/wallets/{id}")
    @Operation(summary = "Delete wallet by ID", description = "Use to delete specific wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response if wallet deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Response if no such wallet in DB")
    })
    public void deleteById(@PathVariable UUID id) {
        walletService.deleteById(id);
    }

    @DeleteMapping("/wallets/erase")
    @Operation(summary = "Delete all wallets", description = "Use to delete all wallets from DB")
    @ApiResponse(responseCode = "200", description = "Response if all wallets deleted successfully from DB")
    public void eraseAllWallets() {
        walletService.deleteAll();
    }

    // operations
    @PostMapping("/wallet")
    @Operation(summary = "Operations with wallet", description = "Use WITHDRAW or DEPOSIT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success - wallet created"),
            @ApiResponse(responseCode = "404", description = "")
    })
    public Map<String, String> processWallet(@RequestBody @Valid OperationTypeRequest request) {
        walletService.process(request);
        return Map.of("operation status", "success");
    }
}
