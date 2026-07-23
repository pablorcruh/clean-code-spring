package ec.com.pablorcruh.bankingtransactions.application.dto;

import ec.com.pablorcruh.bankingtransactions.domain.model.Account;
import ec.com.pablorcruh.bankingtransactions.domain.model.Transaction;

import java.util.List;

public class MapToAccountDetailsDto {


    public static AccountDetailsDto from(Account account){
        List<TransactionDto> transactionDtos =
                account.getTransactions()
                        .stream()
                        .map(MapToAccountDetailsDto::mapTransaction)
                        .toList();
        return new AccountDetailsDto(
                account.getId().value(),
                account.getCustomerId(),
                account.getBalance().getAmount().doubleValue(),
                transactionDtos
        );
    }

    private static TransactionDto mapTransaction(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionType().name(),
                transaction.getAmount().getAmount().doubleValue()
        );
    }
}
