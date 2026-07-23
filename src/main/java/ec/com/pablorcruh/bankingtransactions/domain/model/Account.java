package ec.com.pablorcruh.bankingtransactions.domain.model;

import ec.com.pablorcruh.bankingtransactions.domain.exceptions.InsufficientBalanceException;
import ec.com.pablorcruh.bankingtransactions.domain.exceptions.NegativeMoneyException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {

    private final  AccountId id;
    private final String customerId;
    private Money balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(AccountId id, String customerId, Money initialBalance){
        this.id = id;
        this.customerId = customerId;
        this.balance = initialBalance;
    }

    public void deposit(Money amount){
        Objects.requireNonNull(amount, "Deposit must no be null");
        if(amount.getAmount().compareTo(BigDecimal.ZERO) <=0)
            throw new NegativeMoneyException("deposit must be greater than zero");
        this.balance = this.balance.add(amount);
        this.transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    public void withdraw(Money amount){
        Objects.requireNonNull(amount, "withdraw must no be null");
        if(amount.getAmount().compareTo(BigDecimal.ZERO) <=0)
            throw new NegativeMoneyException("Withdraw must be greater than zero");
        Money newBalance = this.balance.subtract(amount);
        if(newBalance.isNegative())
            throw new InsufficientBalanceException("Insufficient funds for withdraw");
        this.balance = newBalance;
        this.transactions.add(new Transaction(TransactionType.WITHDRAW, amount));
    }

    public List<Transaction> getTransactions(){
        return this.transactions;
    }

    public Money getBalance() {
        return balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public AccountId getId() {
        return id;
    }
}
