package ec.com.pablorcruh.bankingtransactions.application.ports;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.domain.model.AccountId;

public interface GetAccountDetailsUseCase {
    AccountDetailsDto getById(String accountId);
}
