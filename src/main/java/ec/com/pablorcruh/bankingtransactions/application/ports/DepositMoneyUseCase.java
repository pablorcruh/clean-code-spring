package ec.com.pablorcruh.bankingtransactions.application.ports;

import ec.com.pablorcruh.bankingtransactions.application.dto.AccountDetailsDto;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.DepositMoneyCommand;

public interface DepositMoneyUseCase {

    AccountDetailsDto deposit(DepositMoneyCommand command);
}
