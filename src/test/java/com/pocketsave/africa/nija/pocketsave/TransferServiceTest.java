package com.pocketsave.africa.nija.pocketsave;

import com.pocketsave.africa.nija.pocketsave.Dtos.TransferStatusResponse;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.InsufficientFundsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.InvalidAmountException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.SenderWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.pocketService.CustomerService;
import com.pocketsave.africa.nija.pocketsave.pocketService.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransferStatusResponse.TRANSFER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransferServiceTest {
    @Autowired
    private TransferService transferService;



    @Test
    public void testThatCanDoWalletToWalletTransfer_() throws InsufficientFundsException, InvalidAmountException, SenderWalletNotFoundException, PocketWalletNotFoundException {
        Long senderWallet = 1L;
        Long receiverWallet = 2L;
        BigDecimal transferAmount = new BigDecimal(1000);
        WalletToWalletTransferRequest walletToWalletTransferRequest = new WalletToWalletTransferRequest();
        walletToWalletTransferRequest.setSenderWallet(senderWallet);
        walletToWalletTransferRequest.setReceiverWallet(receiverWallet);
        walletToWalletTransferRequest.setAmount(transferAmount);
        walletToWalletTransferRequest.setTransferStatus(TRANSFER.toString());
        WalletToWalletTransferResponse walletToWalletTransferResponse = transferService.walletToWalletTransfer(walletToWalletTransferRequest);
        assertThat(walletToWalletTransferResponse).isNotNull();
    }
}


