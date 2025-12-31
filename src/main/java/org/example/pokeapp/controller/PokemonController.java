package org.example.pokeapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.pokeapp.dto.request.PokemonReqDto;
import org.example.pokeapp.dto.response.PokemonResDto;
import org.example.pokeapp.entity.Pokemon;
import org.example.pokeapp.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/poke")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping("/save-poke")
    public ResponseEntity<String> savePokemon(@RequestBody PokemonReqDto pokemonReqDto) {
        pokemonService.savePokemon(pokemonReqDto.getName());
        return ResponseEntity.ok("pokemons saved successfully");
    }

    @GetMapping("/fetch-pikachu")
    public ResponseEntity<PokemonResDto> fetchPikachu() {
        return ResponseEntity.ok(pokemonService.fetchPikachu());
    }

    @PostMapping("/save/{name}")
    public ResponseEntity<Pokemon> savePokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.savePokemon(name));
    }

    @PostMapping("/bulk-save")
    public ResponseEntity<List<Pokemon>> bulkSavePokemon(@RequestParam(defaultValue = "20") int limit,
                                                         @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(pokemonService.saveAllPokemon(limit, offset));
    }
}
