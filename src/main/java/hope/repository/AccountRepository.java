package hope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import hope.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

}