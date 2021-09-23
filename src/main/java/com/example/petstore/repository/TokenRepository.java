package com.example.petstore.repository;

import com.example.petstore.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByUuid(UUID uuid);
    Optional<Token> getByUuid(UUID uuid);
}
