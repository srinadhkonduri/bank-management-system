package bank_management.service;

import bank_management.model.Account;
import bank_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // 1. creating the account
    public Account createAccount(Account account){
        Validator.validateAccount(
                account.getAccountNumber(),
                account.getMobileNumber(),
                account.getPin()
        );

        account.setBalance(5000.00); // initialize balance

        return repository.save(account);
    }


    // 2. deposit amount
    public Account depositMoney(String mobile, double amount, int pin){
        Account acc = repository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("account not found"));

        if (acc.getPin() != pin){
            throw new RuntimeException("Invalid pin");
        }

        acc.setBalance(acc.getBalance() + amount);

        return repository.save(acc);
    }


    // 3. withdraw money
    public Account withdrawMoney(String mobile, double amount , int pin){
        Account acc = repository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("account not found"));

        if (acc.getPin() != pin){
            throw new RuntimeException("Invalid pin");
        }

        if (acc.getBalance() < amount){
            throw new RuntimeException("invalid balance in the account");
        }

        acc.setBalance(acc.getBalance() - amount);

        return repository.save(acc);
    }


    // 4. check balance
    public double checkBalance(String mobile, int pin){
        Account acc = repository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (acc.getPin() != pin){
            throw new RuntimeException("invalid pin");
        }


        return acc.getBalance();
    }

    // 5. get account by mobile
    public Account getAccountByMobile(String mobile){
        return repository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("account not found"));
    }

    // 6. get all account numbers
    public List<Account> getAllAccountNumbers(){
        return repository.findAll();
    }

    // 7. transfer money from one account to another account
    @Transactional
    public String transferAmount(String senderMobile, String receiverMobile, double amount , int senderPin){
        Account sender = repository.findByMobileNumber(senderMobile)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account receiver = repository.findByMobileNumber(receiverMobile)
                .orElseThrow(() -> new RuntimeException("receiver not found"));

        if (sender.getPin() != senderPin){
            throw new RuntimeException("invalid pin");
        }

        if (sender.getBalance() < amount){
            throw new RuntimeException("insufficient funds in the account");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        repository.save(sender);
        repository.save(receiver);

        return "Transfer Successful";
    }

    public void deleteAccount(String mobile){
        repository.deleteAccountByMobileNumber(mobile);
    }
}
