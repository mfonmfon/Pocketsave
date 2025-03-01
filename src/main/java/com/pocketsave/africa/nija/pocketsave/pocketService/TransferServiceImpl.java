package com.pocketsave.africa.nija.pocketsave.pocketService;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferRequest;
import com.pocketsave.africa.nija.pocketsave.Dtos.WalletToWalletTransferResponse;
import com.pocketsave.africa.nija.pocketsave.data.models.PocketWallet;
import com.pocketsave.africa.nija.pocketsave.data.models.Transfer;
import com.pocketsave.africa.nija.pocketsave.data.repository.PocketWalletRepository;
import com.pocketsave.africa.nija.pocketsave.data.repository.TransferRepository;
import com.pocketsave.africa.nija.pocketsave.exceptions.InsufficientFundsException;
import com.pocketsave.africa.nija.pocketsave.exceptions.InvalidAmountException;
import com.pocketsave.africa.nija.pocketsave.exceptions.PocketWalletNotFoundException;
import com.pocketsave.africa.nija.pocketsave.exceptions.SenderWalletNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static com.pocketsave.africa.nija.pocketsave.Dtos.TransactionStatusResponse.*;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService{
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private PocketWalletRepository pocketWalletRepository;


    @Transactional
    @Override
    public WalletToWalletTransferResponse walletToWalletTransfer(WalletToWalletTransferRequest walletToWalletTransferRequest) throws PocketWalletNotFoundException, InsufficientFundsException,
            InvalidAmountException {
       try{
           PocketWallet senderWallet = pocketWalletRepository.findById(walletToWalletTransferRequest.getSenderWallet())
                   .orElseThrow(()-> new PocketWalletNotFoundException(SENDER_POCKET_WALLET_NOT_FOUND.getMessage()));
           PocketWallet receiverWallet = pocketWalletRepository.findById(walletToWalletTransferRequest.getReceiverWallet())
                   .orElseThrow(() -> new PocketWalletNotFoundException(RECIPIENT_POCKET_WALLET_NOT_FOUND.getMessage()));
           // Validate the amount being transferred is within the available balance in the sender's wallet.
           if(walletToWalletTransferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
               throw new InvalidAmountException(INVALID_AMOUNT.getMessage());
           }
           if(senderWallet.getBalance().compareTo(walletToWalletTransferRequest.getAmount()) < 0){
               throw new InsufficientFundsException(INSUFFICIENT_FUNDS.getMessage());
           }
           senderWallet.setBalance(senderWallet.getBalance().subtract(walletToWalletTransferRequest.getAmount()));
           receiverWallet.setBalance(receiverWallet.getBalance().add(walletToWalletTransferRequest.getAmount()));
//           log.info("Sender wallet transfer id{}", senderWallet.getId());
//           log.info("Receiver wallet id{}", receiverWallet.getId());
           pocketWalletRepository.save(senderWallet);
           pocketWalletRepository.save(receiverWallet);
           Transfer transfer = new Transfer();
           transfer.setTransferAmount(walletToWalletTransferRequest.getAmount());
           transfer.setSenderWallet(senderWallet);
           transfer.setReceiverWallet(receiverWallet);
           transfer.setTransferStatus(walletToWalletTransferRequest.getTransferStatus());
//           log.info("Sender wallet transfer id{}", senderWallet.getId());
           transferRepository.save(transfer);
           WalletToWalletTransferResponse walletToWalletTransferResponse = new WalletToWalletTransferResponse();
           walletToWalletTransferResponse.setTransferResponse(TRANSFER_SUCCESS_MESSAGE.getMessage());
//        walletToWalletTransferResponse.setTransferResponse(transfer.getTransferStatus());
           return walletToWalletTransferResponse;
       }catch(Exception exception){
           throw new RuntimeException("Transaction failed ");
       }
    }
}
