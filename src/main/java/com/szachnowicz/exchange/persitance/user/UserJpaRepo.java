package com.szachnowicz.exchange.persitance.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserJpaRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByPesel(String pesel);
}
