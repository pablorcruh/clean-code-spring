package ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateAccountRequest(
        @NotBlank String customerId,
        @Positive double initialBalance
) {
}
