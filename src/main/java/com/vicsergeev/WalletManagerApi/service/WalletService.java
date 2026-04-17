package com.vicsergeev.WalletManagerApi.service;

/*
 * Created by Vic
 * 16.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.WalletDto;
import com.vicsergeev.WalletManagerApi.dto.WalletDtoMapper;
import com.vicsergeev.WalletManagerApi.entity.Wallet;
import com.vicsergeev.WalletManagerApi.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // CRUD
    public WalletDto create (WalletDto walletDto) {
        Wallet wallet = WalletDtoMapper.mapToEntity(walletDto);
        Wallet saved =  walletRepository.save(wallet);
        return WalletDtoMapper.mapToDto(saved);
    }

    public List<WalletDto> findAll() {
        return walletRepository.findAll()
                .stream()
                .map(WalletDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public WalletDto findById(UUID id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet with id: " + id + " not found"));
        return WalletDtoMapper.mapToDto(wallet);
    }

    public void deleteById(UUID id) {
        walletRepository.deleteById(id);
    }

}
