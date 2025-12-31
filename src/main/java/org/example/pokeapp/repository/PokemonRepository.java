package org.example.pokeapp.repository;

import org.example.pokeapp.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAllByOrderByWeightDesc();
    List<Pokemon> findByWeightBetweenOrderByWeightDesc(Integer min, Integer max);
    List<Pokemon> findByWeightGreaterThanOrderByWeightDesc(Integer min);
}
