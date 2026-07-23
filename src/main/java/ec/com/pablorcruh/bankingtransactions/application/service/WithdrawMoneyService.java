package ec.com.pablorcruh.bankingtransactions.application.service;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.MapToAccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.WithdrawMoneyCommand;
import ec.com.pablorcruh.bankingtransactions.application.ports.WithdrawMoneyUseCase;
import ec.com.pablorcruh.bankingtransactions.domain.exceptions.AccountNotFoundException;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;
import ec.com.pablorcruh.bankingtransactions.domain.model.Money;
import ec.com.pablorcruh.bankingtransactions.domain.ports.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WithdrawMoneyService implements WithdrawMoneyUseCase {

    private final IAccountRepository accountRepository;

    public WithdrawMoneyService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto withdraw(WithdrawMoneyCommand command) {
        var accountId = new AccountId(command.accountId());

        var account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(command.accountId()));
        account.withdraw(Money.of(command.amount()));
        accountRepository.save(account);
        return MapToAccountDetailsDto.from(account);
    }
}
