package ec.com.pablorcruh.bankingtransactions.application.ports;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.CreateAccountCommand;

public interface CreateAccountUseCase {

    AccountDetailsDto createAccount(CreateAccountCommand command);
}
