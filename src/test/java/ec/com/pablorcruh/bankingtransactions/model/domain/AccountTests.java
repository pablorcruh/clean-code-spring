package ec.com.pablorcruh.bankingtransactions.model.domain;

import ec.com.pablorcruh.bankingtransactions.domain.exceptions.InsufficientBalanceException;
import ec.com.pablorcruh.bankingtransactions.domain.model.TransactionType;
import org.junit.jupiter.api.Test;


import ec.com.pablorcruh.bankingtransactions.domain.model.Account;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;
import ec.com.pablorcruh.bankingtransactions.domain.model.Money;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {

    @Test
    void should_deposit_money_correctly(){
        Account account = new Account(AccountId.newId(), "123", Money.of(100));

        account.deposit(Money.of(100));

        assertEquals(Money.of(200).getAmount(), account.getBalance().getAmount());
        assertFalse(account.getTransactions().isEmpty());
        assertEquals(TransactionType.DEPOSIT, account.getTransactions().get(0).getTransactionType());
    }

    @Test
    void should_not_allow_withdraw_when_insufficient_balance(){
        Account account = new Account(AccountId.newId(), "123", Money.of(100));
        InsufficientBalanceException ex = assertThrows(InsufficientBalanceException.class, () -> account.withdraw(Money.of(200)));

        assertNotNull(ex.getMessage());
        assertEquals(Money.of(100).getAmount(), account.getBalance().getAmount());
        assertTrue(account.getTransactions().isEmpty());
    }
}
