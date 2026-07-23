package ec.com.pablorcruh.bankingtransactions.domain.model;

import java.util.UUID;

public record AccountId(String value) {

    public static AccountId newId(){
        return new AccountId(UUID.randomUUID().toString());
    }
}
