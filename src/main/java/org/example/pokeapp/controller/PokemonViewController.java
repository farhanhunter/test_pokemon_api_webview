package org.example.pokeapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.pokeapp.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PokemonViewController {
    private final PokemonService pokemonService;

    @GetMapping("/poke/list")
    public String getPokemonList(@RequestParam(name = "weightType", required = false) Integer weightType, Model model) {
        model.addAttribute("pokemons", pokemonService.getPokemonsByWeight(weightType));
        model.addAttribute("selectedWeightType", weightType != null ? weightType : 3);
        return "pokemons/index";
    }
}
