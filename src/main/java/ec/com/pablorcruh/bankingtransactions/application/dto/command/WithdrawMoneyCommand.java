package ec.com.pablorcruh.bankingtransactions.application.dto.command;

public record WithdrawMoneyCommand(
        String accountId,
        double amount
) {
}
