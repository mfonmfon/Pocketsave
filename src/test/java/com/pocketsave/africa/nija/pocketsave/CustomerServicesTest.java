package com.pocketsave.africa.nija.pocketsave;

import com.pocketsave.africa.nija.pocketsave.Dtos.CreateCustomerPocketSaveAccountRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.CreateCustomerPocketSaveAccountResponse;
import com.pocketsave.africa.nija.pocketsave.Dtos.DepositRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.DepositResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.CustomerNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.pocketService.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.DEPOSIT_SUCCESS_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class CustomerServicesTest {


    @Autowired
    private CustomerService customerService;

    @Test
    public void testThatCustomersCanDepositMoneyIntoTheWallet() throws CustomerNotFoundException, PocketWalletNotFoundException {
        DepositRequest depositRequest = buildCustomerDepositRequest();
        DepositResponse depositResponse = customerService.deposit(depositRequest);
        assertThat(depositResponse.getDepositStatus()).contains(DEPOSIT_SUCCESS_MESSAGE.getMessage());
    }

    private static DepositRequest buildCustomerDepositRequest() {
        BigDecimal amount = new BigDecimal(5000);
        Long customerId = 3L;
        String description = "Description";
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setCustomerId(customerId);
        depositRequest.setAmount(amount);
        depositRequest.setDescription(description);
        return depositRequest;
    }

    @Test
    public void testThatCustomerCanCreatePocketSaveAccount(){
            CreateCustomerPocketSaveAccountRequest createCustomerSaveAccount = new CreateCustomerPocketSaveAccountRequest();
            createCustomerSaveAccount.setFirstName("Paul");
            createCustomerSaveAccount.setLastName("Johnson");
            createCustomerSaveAccount.setEmail("pauljohnson@gmail.com");
            createCustomerSaveAccount.setPhoneNumber("08056690231");
            createCustomerSaveAccount.setPassword("12345Paul!");
            CreateCustomerPocketSaveAccountResponse createCustomerPocketSaveAccountResponse = customerService.createAccount(createCustomerSaveAccount);
            assertThat(createCustomerPocketSaveAccountResponse).isNotNull();

    }
}
