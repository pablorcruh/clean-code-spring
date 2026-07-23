package ec.com.pablorcruh.bankingtransactions.application.dto.command;

public record DepositMoneyCommand(
        String accountId,
        double amount
) {
}
