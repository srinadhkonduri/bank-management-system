package bank_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    private String bankName;
    private String accountHolderName;

    private String accountNumber; // 10 digit
    private String mobileNumber;  // 10 digit

    private Double balance;

    private int pin; // 4 digit

    private String address;
}
