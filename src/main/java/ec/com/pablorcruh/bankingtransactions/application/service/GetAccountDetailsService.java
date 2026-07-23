package ec.com.pablorcruh.bankingtransactions.application.service;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.MapToAccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.ports.GetAccountDetailsUseCase;
import ec.com.pablorcruh.bankingtransactions.domain.exceptions.AccountNotFoundException;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;
import ec.com.pablorcruh.bankingtransactions.domain.ports.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class GetAccountDetailsService implements GetAccountDetailsUseCase {

    private final IAccountRepository accountRepository;

    public GetAccountDetailsService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto getById(String accountId) {
        var id = new AccountId(accountId);
        var account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id.value()));
        return MapToAccountDetailsDto.from(account);
    }
}
