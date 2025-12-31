package org.example.pokeapp.dto.response;

import java.util.List;

public record PokemonResDto (
    int id,
    String name,
    int baseExperience,
    int weight,
    Sprites sprites,
    List<AbilityWrapper> abilities
) {
    public record Sprites(String front_default) {}
}

