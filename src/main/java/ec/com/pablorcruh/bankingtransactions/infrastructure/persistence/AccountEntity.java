package ec.com.pablorcruh.bankingtransactions.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="accounts")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    @Id
    private String id;

    @Column(nullable =false)
    private String idCustomer;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions= new ArrayList<>();

    public void addTransaction(TransactionEntity transaction) {
        transaction.setAccount(this);
        this.transactions.add(transaction);
    }
}
