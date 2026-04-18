package bank_management.repository;

import bank_management.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {


    Optional<Account> findByMobileNumber(String mobileNumber);

    Optional<Account> findByAccountNumber(String accountNumber);

    void deleteAccountByMobileNumber(String mobileNumber);
}
