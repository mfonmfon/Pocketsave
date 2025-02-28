package com.pocketsave.africa.nija.pocketsave.pocketService;

import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositResponse;
import com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse;
import com.pocketsave.africa.nija.pocketsave.data.models.PocketWallet;
import com.pocketsave.africa.nija.pocketsave.data.repository.PocketWalletRepository;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.DEPOSIT_SUCCESS_MESSAGE;

@Service
public class PocketWalletServiceImpl implements PocketWalletService{

    private final PocketWalletRepository pocketWalletRepository;

    public PocketWalletServiceImpl(PocketWalletRepository pocketWalletRepository) {
        this.pocketWalletRepository = pocketWalletRepository;
    }

    @Override
    public PocketWalletDepositResponse deposit(PocketWalletDepositRequest walletDepositRequest) throws PocketWalletNotFoundException {
        PocketWallet pocketWallet = pocketWalletRepository.findById(walletDepositRequest.getWalletId())
                .orElseThrow(()-> new PocketWalletNotFoundException("Wallet not found"));
        pocketWallet.setBalance(pocketWallet.getBalance().add(walletDepositRequest.getAmount()));
        pocketWalletRepository.save(pocketWallet);
        PocketWalletDepositResponse pocketWalletDepositResponse = new PocketWalletDepositResponse();
        pocketWalletDepositResponse.setTransactionStatusResponse(DEPOSIT_SUCCESS_MESSAGE.getMessage());
        return pocketWalletDepositResponse;
    }
}
