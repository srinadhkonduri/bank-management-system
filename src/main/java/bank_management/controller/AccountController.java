package bank_management.controller;

import bank_management.model.Account;
import bank_management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountController {

    @Autowired
    private AccountService service;

    // creating the account
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
        return service.createAccount(account);
    }


    // deposit
    @PostMapping("/deposit")
    public Account depositMoney(@RequestParam String mobile,
                                @RequestParam double amount,
                                @RequestParam int pin){
        return service.depositMoney(mobile,amount,pin);
    }

    // withdraw
    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String mobile,
                            @RequestParam double amount,
                            @RequestParam int pin){
        return service.withdrawMoney(mobile,amount,pin);
    }

    // balance
    @GetMapping("/balance")
    public double balance(@RequestParam String mobile,
                          @RequestParam int pin) {
        return service.checkBalance(mobile, pin);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String senderMobile,
                           @RequestParam String receiverMobile,
                           @RequestParam double amount,
                           @RequestParam int pin) {
        return service.transferAmount(senderMobile, receiverMobile, amount, pin);
    }

    @GetMapping("/account/{mobile}")
    public Account getAccount(@PathVariable String mobile) {
        return service.getAccountByMobile(mobile);
    }

    @GetMapping("/all")
    public List<Account> getAll() {
        return service.getAllAccountNumbers();
    }

    @DeleteMapping("/delete{mobile}")
    public void deleteAccount(@PathVariable String mobile){
        service.deleteAccount(mobile);
        System.out.println("successfully deleted account by using mobile number");
    }

    @PutMapping("/update{accountNumber}")
    public void updateAccounts(@PathVariable String mobile, @PathVariable String accountNUmber){
        System.out.println("method for updating the mobile number by using account number");
    }

    public void hello(){
        System.out.println("hello world");
    }

}
