package com.pocketsave.africa.nija.pocketsave.data.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal transferAmount;
    private String TransferStatus;
    @ManyToOne
    @JoinColumn(name = "sender_wallet_id", referencedColumnName = "id")
    private PocketWallet senderWallet;
    @ManyToOne
    @JoinColumn(name="receiver_wallet_id", referencedColumnName = "id", nullable = false)
    private PocketWallet receiverWallet;
//    @DateTimeFormat
    private LocalDateTime transferDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferStatus() {
        return TransferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        TransferStatus = transferStatus;
    }

    public PocketWallet getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(PocketWallet senderWallet) {
        this.senderWallet = senderWallet;
    }

    public PocketWallet getReceiverWallet() {
        return receiverWallet;
    }

    public void setReceiverWallet(PocketWallet receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }
}
