package com.pocketsave.africa.nija.pocketsave.Dtos;

public enum TransactionStatusResponse {
    DEPOSIT_SUCCESS_MESSAGE("Deposit successfully"),
    WITHDRAWAL_SUCCESS_MESSAGE("Withdrawal successful"),
    TRANSFER_SUCCESS_MESSAGE("Transfer successful"),
    SENDER_POCKET_WALLET_NOT_FOUND("Sender not found"), //
    RECIPIENT_POCKET_WALLET_NOT_FOUND("Recipient not found"), //
    INSUFFICIENT_FUNDS("Insufficient funds"), //
    TRANSACTION_LIMIT_REACHED("Transaction limit reached"), //
    INVALID_AMOUNT("Invalid amount"), //
    CREATE_ACCOUNT_SUCCESS_MESSAGE("Create account successfully");

    private final String message;

    TransactionStatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
