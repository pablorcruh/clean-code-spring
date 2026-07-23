package ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.response;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request.TransactionSummary;

import java.util.List;

public record AccountResponse(
        String id,
        String customerId,
        double balance,
        List<TransactionSummary> transactions
) {

    public static AccountResponse fromDto(AccountDetailsDto dto){
        List<TransactionSummary> transactions = dto.transactions()
                .stream()
                .map(transaction -> new TransactionSummary(
                        transaction.id(),
                        transaction.type(),
                        transaction.amount()
                )).toList();
        return new AccountResponse(
          dto.id(),
          dto.cutomerId(),
          dto.balance(),
          transactions
        );
    }
}
