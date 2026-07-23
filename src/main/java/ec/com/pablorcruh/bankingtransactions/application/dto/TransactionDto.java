package ec.com.pablorcruh.bankingtransactions.application.dto;

public record TransactionDto(
        String id,
        String type,
        double amount
) {
}
