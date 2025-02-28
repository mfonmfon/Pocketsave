package com.pocketsave.africa.nija.pocketsave;

import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.PocketWalletDepositResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.pocketService.PocketWalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.DEPOSIT_SUCCESS_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PocketWalletServiceTest {

    @Autowired
    private PocketWalletService pocketWalletService;


    @Test
    public void testThatCanDepositInWallet() throws PocketWalletNotFoundException {
        BigDecimal walletAmount = new BigDecimal(5000);
        Long walletId = 1L;
        PocketWalletDepositRequest walletDepositRequest = new PocketWalletDepositRequest();
        walletDepositRequest.setAmount(walletAmount);
        walletDepositRequest.setWalletId(walletId);
        PocketWalletDepositResponse walletDepositResponse = pocketWalletService.deposit(walletDepositRequest);
        assertThat(walletDepositResponse.getTransactionStatusResponse()).contains(DEPOSIT_SUCCESS_MESSAGE.getMessage());
        assertNotNull(walletDepositResponse);
    }


}
