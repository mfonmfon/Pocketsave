package com.pocketsave.africa.nija.pocketsave.Dtos;

import lombok.Getter;
import lombok.Setter;


public class PocketWalletDepositResponse {
   private String transactionStatusResponse;

    public String getTransactionStatusResponse() {
        return transactionStatusResponse;
    }

    public void setTransactionStatusResponse(String transactionStatusResponse) {
        this.transactionStatusResponse = transactionStatusResponse;
    }
}
