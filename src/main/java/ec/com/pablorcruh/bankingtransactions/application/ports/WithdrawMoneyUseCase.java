package ec.com.pablorcruh.bankingtransactions.application.ports;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.WithdrawMoneyCommand;

public interface WithdrawMoneyUseCase {

    AccountDetailsDto withdraw(WithdrawMoneyCommand command);
}
