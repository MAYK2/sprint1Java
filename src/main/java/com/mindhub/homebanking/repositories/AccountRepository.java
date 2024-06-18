package com.mindhub.homebanking.repositories;
import com.mindhub.homebanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
   List<Account> findByClientUsername(String username);
   Account findByNumberAccount(String number);
}
