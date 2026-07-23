package ec.com.pablorcruh.bankingtransactions.infrastructure.web.controller;

import ec.com.pablorcruh.bankingtransactions.application.dto.command.CreateAccountCommand;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.DepositMoneyCommand;
import ec.com.pablorcruh.bankingtransactions.application.dto.command.WithdrawMoneyCommand;
import ec.com.pablorcruh.bankingtransactions.application.ports.CreateAccountUseCase;
import ec.com.pablorcruh.bankingtransactions.application.ports.DepositMoneyUseCase;
import ec.com.pablorcruh.bankingtransactions.application.ports.GetAccountDetailsUseCase;
import ec.com.pablorcruh.bankingtransactions.application.ports.WithdrawMoneyUseCase;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request.CreateAccountRequest;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request.DepositRequest;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request.WithdrawRequest;
import ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.response.AccountResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private final CreateAccountUseCase createAccountUseCase;
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final GetAccountDetailsUseCase getAccountDetailsUseCase;


    public AccountController(CreateAccountUseCase createAccountUseCase, DepositMoneyUseCase depositMoneyUseCase, WithdrawMoneyUseCase withdrawMoneyUseCase, GetAccountDetailsUseCase getAccountDetailsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.depositMoneyUseCase = depositMoneyUseCase;
        this.withdrawMoneyUseCase = withdrawMoneyUseCase;
        this.getAccountDetailsUseCase = getAccountDetailsUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponse>  create(
            @Valid
            @RequestBody
            CreateAccountRequest request){
        var command = new CreateAccountCommand(request.customerId(), request.initialBalance());
        var dto = createAccountUseCase.createAccount(command);
        return ResponseEntity.status(201).body(AccountResponse.fromDto(dto));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable String id,
           @Valid @RequestBody DepositRequest request){
        var command = new DepositMoneyCommand(id, request.amount());
        var dto = depositMoneyUseCase.deposit(command);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));

    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable String id,
            @Valid @RequestBody WithdrawRequest request
    ){
        var command = new WithdrawMoneyCommand(id, request.amount());
        var dto = withdrawMoneyUseCase.withdraw(command);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(
            @PathVariable String id
    ){
        var dto = getAccountDetailsUseCase.getById(id);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));
    }
}
