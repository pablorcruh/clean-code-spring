package ec.com.pablorcruh.bankingtransactions.domain.ports;

import ec.com.pablorcruh.bankingtransactions.domain.model.Account;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;

import java.util.Optional;

public interface IAccountRepository {

    Account save(Account account);

    Optional<Account> findById(AccountId accountId);
}
