package ec.com.pablorcruh.bankingtransactions.application.dto;

import java.util.List;

public record AccountDetailsDto(
    String id,
    String cutomerId,
    double balance,
    List<TransactionDto> transactions
) {
}
