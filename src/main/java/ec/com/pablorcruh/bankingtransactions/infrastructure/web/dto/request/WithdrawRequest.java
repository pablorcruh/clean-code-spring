package ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request;

import jakarta.validation.constraints.Positive;

public record WithdrawRequest(
        @Positive double amount
) {
}
