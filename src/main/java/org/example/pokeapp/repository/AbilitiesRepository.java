package org.example.pokeapp.repository;

import org.example.pokeapp.entity.Abilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilitiesRepository extends JpaRepository<Abilities, Long> {
    Optional<Abilities> findByName(String name);
}
