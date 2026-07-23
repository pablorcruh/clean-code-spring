package ec.com.pablorcruh.bankingtransactions.infrastructure.mapper;

import ec.com.pablorcruh.bankingtransactions.domain.model.*;
import ec.com.pablorcruh.bankingtransactions.infrastructure.persistence.AccountEntity;
import ec.com.pablorcruh.bankingtransactions.infrastructure.persistence.TransactionEntity;

public class AccountMapper {

    public static Account toDomain(AccountEntity entity){
        Account account = new Account(
                new AccountId(entity.getId()),
                entity.getIdCustomer(),
                Money.from(entity.getBalance())
        );

        entity.getTransactions().forEach(te -> {
            Transaction tx = new Transaction(
                    TransactionType.valueOf(te.getType()),
                    Money.from(te.getAmount())
            );
            account.getTransactions().add(tx);
        });
        return account;
    }

    public static AccountEntity toEntity(Account account){
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId().value());
        entity.setIdCustomer(account.getCustomerId());
        entity.setBalance(account.getBalance().getAmount());

        entity.getTransactions().clear();
        for(Transaction t: account.getTransactions()){
            TransactionEntity tx = new TransactionEntity();
            tx.setId(t.getId());
            tx.setType(t.getTransactionType().name());
            tx.setAmount(t.getAmount().getAmount());
            entity.addTransaction(tx);
        }
        return entity;
    }
}
