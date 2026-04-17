package com.vicsergeev.WalletManagerApi.controller;

/*
 * Created by Vic
 * 17.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.WalletDto;
import com.vicsergeev.WalletManagerApi.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public WalletDto create(@RequestBody WalletDto dto) {
        return walletService.create(dto);
    }

    @GetMapping
    public List<WalletDto> getAll() {
        return walletService.findAll();
    }

    @GetMapping("/{id}")
    public WalletDto getById(@PathVariable UUID id) {
        return walletService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        walletService.deleteById(id);
    }
}
