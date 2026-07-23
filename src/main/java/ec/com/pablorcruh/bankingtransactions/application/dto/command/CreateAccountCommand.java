package ec.com.pablorcruh.bankingtransactions.application.dto.command;

public record CreateAccountCommand(
    String customerId,
    double initialBalance
) {
}
