package com.pocketsave.africa.nija.pocketsave.Dtos;

public enum TransactionStatusResponse {
    DEPOSIT_SUCCESS_MESSAGE("Deposit successfully"),
    WITHDRAWAL_SUCCESS_MESSAGE("Withdrawal successful"),
    TRANSFER_SUCCESS_MESSAGE("Transfer successful");

    private final String message;

    TransactionStatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
