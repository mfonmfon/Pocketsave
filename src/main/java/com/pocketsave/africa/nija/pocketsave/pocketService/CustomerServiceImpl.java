package com.pocketsave.africa.nija.pocketsave.pocketService;

import com.pocketsave.africa.nija.pocketsave.Dtos.*;
import com.pocketsave.africa.nija.pocketsave.data.models.Customers;
import com.pocketsave.africa.nija.pocketsave.data.models.PocketWallet;
import com.pocketsave.africa.nija.pocketsave.data.repository.CustomersRepository;
import com.pocketsave.africa.nija.pocketsave.data.repository.PocketWalletRepository;
import com.pocketsave.africa.nija.pocketsave.exceptions.AlreadyExistsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.CustomerNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.InvalidCredentialsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.CREATE_ACCOUNT_SUCCESS_MESSAGE;

@Service

public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private  CustomersRepository customersRepository;
    @Autowired
    private PocketWalletRepository pocketWalletRepository;
    @Autowired
    private PocketWalletService pocketWalletService;

//    @Autowired
//    private static final PasswordEncoder passwordEncoder;
    private static final Pattern CUSTOMER_EMAIL_REGEX_VALIDATOR = Pattern.compile("\"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern CUSTOMER_PHONE_NUMBER_REGEX_VALIDATOR = Pattern.compile("^[0-9]{10}$");
    private static final Pattern CUSTOMER_PASSWORD_REGEX_VALIDATOR = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

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
    public CreateCustomerPocketSaveAccountResponse createAccount(CreateCustomerPocketSaveAccountRequest createCustomerSaveAccount) throws AlreadyExistsException, InvalidCredentialsException {
        validateCustomersEmail(createCustomerSaveAccount.getEmail());
        Customers customers = buildCustomersPocketAccount(createCustomerSaveAccount);
        PocketWallet pocketWallet = new PocketWallet();
        pocketWallet.setBalance(BigDecimal.ZERO);
        pocketWallet.setCustomer(customers);
        customers.setPocketWallet(pocketWallet);
        customersRepository.save(customers);
        CreateCustomerPocketSaveAccountResponse createCustomerPocketSaveAccountResponse = new CreateCustomerPocketSaveAccountResponse();
        createCustomerPocketSaveAccountResponse.setResponseMessage(CREATE_ACCOUNT_SUCCESS_MESSAGE.getMessage());
        return createCustomerPocketSaveAccountResponse;

    }

    private void validateCustomersEmail(String email) throws AlreadyExistsException {
        boolean isCustomersEmailExist = customersRepository.existsByEmail(email);
        if(isCustomersEmailExist){
            throw new AlreadyExistsException("Customer with this email "+ email + "already exist");
        }
    }

    private static Customers buildCustomersPocketAccount(CreateCustomerPocketSaveAccountRequest createCustomerSaveAccount) throws InvalidCredentialsException {
        Customers customers = new Customers();
        customers.setFirstName(createCustomerSaveAccount.getFirstName());
        customers.setLastName(createCustomerSaveAccount.getLastName());
        customers.setEmail(validateCustomersEmailAddress(createCustomerSaveAccount.getEmail()));
        customers.setPhoneNumber(validateCustomersPhoneNumber(createCustomerSaveAccount.getPhoneNumber()));
        customers.setPassword((createCustomerSaveAccount.getPassword()));
        return customers;
    }

//    private static String validateCustomersPassword(String password) throws InvalidCredentialsException {
//        if (!password.matches(CUSTOMER_PASSWORD_REGEX_VALIDATOR.pattern())) {
//            throw new InvalidCredentialsException("""
//                Password must contain at least 8 characters,
//                including at least one uppercase letter,
//                one lowercase letter,
//                one digit,
//                and one special character""");
//        }
//        return passwordEncoder.encode(password);
//    }

    private static String validateCustomersPhoneNumber(String phoneNumber) throws InvalidCredentialsException {
        if (!phoneNumber.matches(CUSTOMER_PHONE_NUMBER_REGEX_VALIDATOR.pattern())){
            throw new InvalidCredentialsException("Wrong phone number");
        }
        return phoneNumber;
    }

    private static String validateCustomersEmailAddress(String email) throws InvalidCredentialsException {
        if (email == null ||  CUSTOMER_EMAIL_REGEX_VALIDATOR.matcher(email).matches()){
            throw new InvalidCredentialsException("Wrong email or password");
        }
        return email;
    }
}
