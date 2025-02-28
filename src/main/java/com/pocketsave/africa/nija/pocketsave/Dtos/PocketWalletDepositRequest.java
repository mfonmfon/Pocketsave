package com.pocketsave.africa.nija.pocketsave.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class PocketWalletDepositRequest {
    private Long walletId;
    private BigDecimal amount;
    private BigDecimal balance;

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
