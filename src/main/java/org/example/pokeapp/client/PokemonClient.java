package org.example.pokeapp.client;

import org.example.pokeapp.dto.response.PokemonListResDto;
import org.example.pokeapp.dto.response.PokemonResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pokemonClient", url = "https://pokeapi.co/api/v2")
public interface PokemonClient {

    @GetMapping("/pokemon/{name}")
    PokemonResDto getPokemonByName(@PathVariable("name") String name);

    @GetMapping("/pokemon")
    PokemonListResDto getPokemonList(@RequestParam("limit") int limit,
                        @RequestParam("offset") int offset);
}

