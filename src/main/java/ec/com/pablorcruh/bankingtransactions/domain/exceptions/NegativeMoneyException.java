package ec.com.pablorcruh.bankingtransactions.domain.exceptions;

public class NegativeMoneyException extends RuntimeException{

    public NegativeMoneyException(String message) {
        super(message);
    }
}
