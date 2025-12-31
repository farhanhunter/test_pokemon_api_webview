package org.example.pokeapp.dto.response;

import java.util.List;

public record PokemonListResDto(
    int count,
    String next,
    String previous,
    List<PokemonResult> results
) {
    public record PokemonResult(
        String name,
        String url
    ) {}
}
