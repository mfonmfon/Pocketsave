package com.pocketsave.africa.nija.pocketsave.Dtos;

import com.pocketsave.africa.nija.pocketsave.data.models.PocketWallet;

import java.math.BigDecimal;

public class WalletToWalletTransferRequest {
    private Long senderWallet;
    private Long receiverWallet;
    private BigDecimal amount;
    private String TransferStatus;

    public Long getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(Long senderWallet) {
        this.senderWallet = senderWallet;
    }

    public Long getReceiverWallet() {
        return receiverWallet;
    }

    public void setReceiverWallet(Long receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferStatus() {
        return TransferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        TransferStatus = transferStatus;
    }
}
