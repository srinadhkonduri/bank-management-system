package bank_management.service;



public class Validator {

    public static void validateAccount(String accountNumber, String mobile, int pin){

        if (!accountNumber.matches("\\d{10}")){
            throw new RuntimeException("Account number must 10 digits");
        }

        if (!mobile.matches("\\d{10}")){
            throw new RuntimeException("Mobile number must be 10 digits");
        }

        long mob = Long.parseLong(mobile);
        if (mob < 6000000000L || mob > 9999999999L){
            throw new RuntimeException("invalid mobile range");
        }

        if (pin < 1000 || pin > 9999){
            throw new RuntimeException("invalid pin number");
        }
    }
}
