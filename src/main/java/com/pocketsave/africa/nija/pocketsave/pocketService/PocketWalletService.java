package com.pocketsave.africa.nija.pocketsave.pocketService;

import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;

public interface PocketWalletService {
    PocketWalletDepositResponse deposit(PocketWalletDepositRequest walletDepositRequest) throws PocketWalletNotFoundException;

}
