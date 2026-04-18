package com.vicsergeev.WalletManagerApi.service;

/*
 * Created by Vic
 * 16.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.*;
import com.vicsergeev.WalletManagerApi.entity.Wallet;
import com.vicsergeev.WalletManagerApi.exception.CustomInsufficientFundsException;
import com.vicsergeev.WalletManagerApi.exception.CustomNotFoundException;
import com.vicsergeev.WalletManagerApi.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public WalletDto create(WalletCreateRequest walletCreateRequest) {
        Wallet wallet = new  Wallet();
        wallet.setBalance(walletCreateRequest.getInitialBalance());
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
                .orElseThrow(() -> new CustomNotFoundException(
                        "Wallet with id: " + id + " not found"
                ));
        return WalletDtoMapper.mapToDto(wallet);
    }

    public void deleteById(UUID id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(
                        "Wallet with id: " + id + " not found"
                ));
        walletRepository.deleteById(id);
    }

    public void deleteAll() {
        walletRepository.deleteAll();
    }

    // Operations logic
    @Transactional
    public void process(OperationTypeRequest request) {
        Wallet wallet = walletRepository.findByIdForUpdate(request.getWalletId())
                .orElseThrow(() -> new CustomNotFoundException("Wallet with id: " + request.getWalletId() + " not found"));

        if (request.getOperationType() == OperationType.WITHDRAW) {
            if (wallet.getBalance() < request.getAmount()) {
                throw new CustomInsufficientFundsException("sorry, insufficient funds");
            }
            wallet.setBalance(wallet.getBalance() - request.getAmount());
        } else {
            wallet.setBalance(wallet.getBalance() + request.getAmount());
        }
    }
}
