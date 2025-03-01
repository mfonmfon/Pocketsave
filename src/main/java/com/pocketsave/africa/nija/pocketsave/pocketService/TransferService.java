package com.pocketsave.africa.nija.pocketsave.pocketService;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.InsufficientFundsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.InvalidAmountException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.SenderWalletNotFoundException;

public interface TransferService {
    WalletToWalletTransferResponse walletToWalletTransfer(WalletToWalletTransferRequest walletToWalletTransferRequest) throws SenderWalletNotFoundException, PocketWalletNotFoundException, InsufficientFundsException, InvalidAmountException;
}
