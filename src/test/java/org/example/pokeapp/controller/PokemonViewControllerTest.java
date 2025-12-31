package org.example.pokeapp.controller;

import org.example.pokeapp.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Test
    public void testGetPokemonList() throws Exception {
        when(pokemonService.getPokemonsByWeight(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/poke/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("pokemons/index"));
    }

    @Test
    public void testGetPokemonListWithFilter() throws Exception {
        when(pokemonService.getPokemonsByWeight(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/poke/list").param("weightType", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("pokemons/index"));
    }
}
