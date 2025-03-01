package com.pocketsave.africa.nija.pocketsave.pocketService;

import com.pocketsave.africa.nija.pocketsave.Dtos.CreateCustomerPocketSaveAccountRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.CreateCustomerPocketSaveAccountResponse;
import com.pocketsave.africa.nija.pocketsave.Dtos.DepositRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.DepositResponse;
import com.pocketsave.africa.nija.pocketsave.exceptions.AlreadyExistsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.CustomerNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.InvalidCredentialsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;

public interface CustomerService {

     DepositResponse deposit(DepositRequest depositRequest) throws CustomerNotFoundException, PocketWalletNotFoundException;

     CreateCustomerPocketSaveAccountResponse createAccount(CreateCustomerPocketSaveAccountRequest createCustomerSaveAccount) throws AlreadyExistsException, InvalidCredentialsException;

}
