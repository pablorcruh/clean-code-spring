package ec.com.pablorcruh.bankingtransactions.infrastructure.repository;

import ec.com.pablorcruh.bankingtransactions.infrastructure.persistence.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, String> {
}
