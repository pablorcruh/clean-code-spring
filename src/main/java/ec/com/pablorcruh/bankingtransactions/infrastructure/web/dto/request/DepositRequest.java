package ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request;

import jakarta.validation.constraints.Positive;

public record DepositRequest(
       @Positive double amount
) {
}
