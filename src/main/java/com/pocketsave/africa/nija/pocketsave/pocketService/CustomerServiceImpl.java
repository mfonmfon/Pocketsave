package com.pocketsave.africa.nija.pocketsave.pocketService;

import com.pocketsave.africa.nija.pocketsave.Dtos.*;
import com.pocketsave.africa.nija.pocketsave.data.models.Customers;
import com.pocketsave.africa.nija.pocketsave.data.models.PocketWallet;
import com.pocketsave.africa.nija.pocketsave.data.repository.CustomersRepository;
import com.pocketsave.africa.nija.pocketsave.data.repository.PocketWalletRepository;
import com.pocketsave.africa.nija.pocketsave.exceptions.CustomerNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.CREATE_ACCOUNT_SUCCESS_MESSAGE;

@Service

public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private  CustomersRepository customersRepository;
    @Autowired
    private PocketWalletRepository pocketWalletRepository;
    @Autowired
    private PocketWalletService pocketWalletService;



    @Override
    public DepositResponse deposit(DepositRequest depositRequest) throws CustomerNotFoundException, PocketWalletNotFoundException {
        Customers customers = customersRepository.findById(depositRequest.getCustomerId())
                .orElseThrow(()-> new CustomerNotFoundException("Customer Not Found "+ depositRequest.getCustomerId()));
        PocketWallet pocketWallet = customers.getPocketWallet();
        pocketWallet.setBalance(pocketWallet.getBalance().add(depositRequest.getAmount()));
        PocketWalletDepositRequest pocketWalletDepositRequest = new PocketWalletDepositRequest();
        pocketWalletDepositRequest.setAmount(depositRequest.getAmount());
        pocketWalletDepositRequest.setWalletId(pocketWallet.getId());
        PocketWalletDepositResponse pocketWalletDepositResponse = pocketWalletService.deposit(pocketWalletDepositRequest);
        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setDepositStatus(pocketWalletDepositResponse.getTransactionStatusResponse());
        return depositResponse;
    }

    @Override
    public CreateCustomerPocketSaveAccountResponse createAccount(CreateCustomerPocketSaveAccountRequest createCustomerSaveAccount) {
        Customers customers = new Customers();
        customers.setFirstName(createCustomerSaveAccount.getFirstName());
        customers.setLastName(createCustomerSaveAccount.getLastName());
        customers.setEmail(createCustomerSaveAccount.getEmail());
        customers.setPhoneNumber(createCustomerSaveAccount.getPhoneNumber());
        customers.setPassword(createCustomerSaveAccount.getPassword());
        PocketWallet pocketWallet = new PocketWallet();
        pocketWallet.setBalance(BigDecimal.ZERO);
        pocketWallet.setCustomer(customers);
        customers.setPocketWallet(pocketWallet);
        customersRepository.save(customers);
        CreateCustomerPocketSaveAccountResponse createCustomerPocketSaveAccountResponse = new CreateCustomerPocketSaveAccountResponse();
        createCustomerPocketSaveAccountResponse.setResponseMessage(CREATE_ACCOUNT_SUCCESS_MESSAGE.getMessage());
        return createCustomerPocketSaveAccountResponse;

    }
}
