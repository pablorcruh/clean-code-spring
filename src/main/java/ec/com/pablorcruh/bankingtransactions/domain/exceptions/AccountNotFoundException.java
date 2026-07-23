package ec.com.pablorcruh.bankingtransactions.domain.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String id) {
        super("Account not found: " + id);
    }
}
