package org.example.pokeapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PokemonResDto (
    int id,
    String name,
    @JsonProperty("base_experience")
    int baseExperience,
    int weight,
    Sprites sprites,
    List<AbilityWrapper> abilities
) {
    public record Sprites(String front_default) {}
}

