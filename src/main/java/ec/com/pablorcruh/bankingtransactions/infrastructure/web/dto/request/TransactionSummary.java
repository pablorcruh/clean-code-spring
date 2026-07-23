package ec.com.pablorcruh.bankingtransactions.infrastructure.web.dto.request;

public record TransactionSummary(
        String id,
        String type,
        double amount
) {
}
