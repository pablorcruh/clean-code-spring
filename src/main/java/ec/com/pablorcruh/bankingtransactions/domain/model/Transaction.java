package ec.com.pablorcruh.bankingtransactions.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Transaction {

    private final String id;
    private final TransactionType transactionType;
    private final Money amount;
    private final Instant createdAt;

    public Transaction(TransactionType transactionType, Money amount) {
        this.id = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Money getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
