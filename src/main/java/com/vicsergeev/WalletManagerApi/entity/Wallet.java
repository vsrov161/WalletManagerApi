package com.vicsergeev.WalletManagerApi.entity;

/*
 * Created by Vic
 * 16.04.2026
 */

import com.vicsergeev.WalletManagerApi.dto.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private Long balance;
}
