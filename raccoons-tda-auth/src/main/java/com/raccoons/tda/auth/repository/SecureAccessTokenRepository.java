package com.raccoons.tda.auth.repository;

import com.raccoons.tda.auth.model.token.SecureAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface SecureAccessTokenRepository extends JpaRepository<SecureAccessToken, Long> {

    CompletableFuture<Optional<SecureAccessToken>> findOneByOwner(final String owner);

}
