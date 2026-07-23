package ec.com.pablorcruh.bankingtransactions.application.service;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.MapToAccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.CreateAccountCommand;
import ec.com.pablorcruh.bankingtransactions.application.ports.CreateAccountUseCase;
import ec.com.pablorcruh.bankingtransactions.domain.model.Account;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;
import ec.com.pablorcruh.bankingtransactions.domain.model.Money;
import ec.com.pablorcruh.bankingtransactions.domain.ports.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountService implements CreateAccountUseCase {

    private final IAccountRepository accountRepository;

    public CreateAccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto createAccount(CreateAccountCommand command) {
        Account account = new Account(
                AccountId.newId(),
                command.customerId(),
                Money.of(command.initialBalance())
        );
        Account saved = accountRepository.save(account);
        return MapToAccountDetailsDto.from(saved);
    }
}
