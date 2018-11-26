package com.example.game.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String>{
		Account getByName(String name);
}
