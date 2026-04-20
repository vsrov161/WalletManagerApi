package com.vicsergeev.WalletManagerApi.service;

/*
 * Created by Vic
 * 16.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.*;
import com.vicsergeev.WalletManagerApi.entity.Wallet;
import com.vicsergeev.WalletManagerApi.exception.CustomInsufficientFundsException;
import com.vicsergeev.WalletManagerApi.exception.CustomNotFoundException;
import com.vicsergeev.WalletManagerApi.exception.CustomValidateException;
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
        wallet.setWalletTitle(walletCreateRequest.getWalletTitle());
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

    @Transactional(rollbackFor = Exception.class)
    public WalletDto updateById(UUID id, WalletUpdateRequest request) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(
                        "Wallet with id: " + id + " not found"
                ));
        if (request.getUpdatedWalletTitle() == null) {
            throw new CustomValidateException("wallet title can not be null");
        }
        wallet.setWalletTitle(request.getUpdatedWalletTitle());
        walletRepository.save(wallet);
        return WalletDtoMapper.mapToDto(wallet);
    }

    // Operations logic
    @Transactional(rollbackFor = Exception.class)
    public void process(OperationTypeRequest request) {

        UUID walletUUID;
        try {
            walletUUID = UUID.fromString(request.getWalletId());
        }  catch (IllegalArgumentException e) {
            throw new CustomValidateException("invalid UUID format: "  + request.getWalletId());
        }

        Wallet wallet = walletRepository.findByIdForUpdate(walletUUID)
                .orElseThrow(() -> new CustomNotFoundException("Wallet with id: " + request.getWalletId() + " not found"));

        Long amount = request.getAmount();
        if (amount == null) {
            throw new CustomValidateException("amount can not be null");
        }

        if (request.getOperationType() == OperationType.WITHDRAW) {
            if (wallet.getBalance() < amount) {
                throw new CustomInsufficientFundsException("insufficient funds");
            }
            wallet.setBalance(wallet.getBalance() - amount);
        } else {
            wallet.setBalance(wallet.getBalance() + amount);
        }
    }
}
