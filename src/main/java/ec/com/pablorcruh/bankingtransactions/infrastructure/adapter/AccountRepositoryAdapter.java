package ec.com.pablorcruh.bankingtransactions.infrastructure.adapter;

import ec.com.pablorcruh.bankingtransactions.domain.model.Account;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;
import ec.com.pablorcruh.bankingtransactions.domain.ports.IAccountRepository;
import ec.com.pablorcruh.bankingtransactions.infrastructure.mapper.AccountMapper;
import ec.com.pablorcruh.bankingtransactions.infrastructure.persistence.AccountEntity;
import ec.com.pablorcruh.bankingtransactions.infrastructure.repository.SpringDataAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryAdapter implements IAccountRepository {

    private final SpringDataAccountRepository springDataAccountRepository;

    public AccountRepositoryAdapter(SpringDataAccountRepository springDataAccountRepository) {
        this.springDataAccountRepository = springDataAccountRepository;
    }


    @Override
    public Account save(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);
        AccountEntity savedEntity = springDataAccountRepository.save(entity);
        return AccountMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return springDataAccountRepository.findById(accountId.value())
                .map(AccountMapper::toDomain);
    }
}
