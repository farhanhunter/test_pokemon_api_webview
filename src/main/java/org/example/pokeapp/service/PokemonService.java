package org.example.pokeapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pokeapp.client.PokemonClient;
import org.example.pokeapp.dto.response.PokemonListResDto;
import org.example.pokeapp.dto.response.PokemonResDto;
import org.example.pokeapp.entity.Abilities;
import org.example.pokeapp.entity.Pokemon;
import org.example.pokeapp.entity.RelationAbilities;
import org.example.pokeapp.repository.AbilitiesRepository;
import org.example.pokeapp.repository.PokemonRepository;
import org.example.pokeapp.repository.RelationAbilitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonService {
    private final PokemonClient pokemonClient;
    private final PokemonRepository pokemonRepository;
    private final AbilitiesRepository abilitiesRepository;
    private final RelationAbilitiesRepository relationAbilitiesRepository;

    public PokemonResDto fetchPikachu() {
        return pokemonClient.getPokemonByName("pikachu");
    }

    @Transactional
    public Pokemon savePokemon(String name) {
        PokemonResDto pokemonResDto = pokemonClient.getPokemonByName(name);

        if (pokemonResDto.id() < 1 || pokemonResDto.id() > 200) {
            return null;
        }

        if (pokemonResDto.weight() < 100) {
            return null;
        }

        String imageUrl = pokemonResDto.sprites().front_default();
        String localImagePath = downloadImage(imageUrl, name);

        Pokemon pokemon = Pokemon.builder()
                .name(pokemonResDto.name())
                .baseExperience(pokemonResDto.baseExperience())
                .weight(pokemonResDto.weight())
                .imagePath(localImagePath)
                .build();

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        pokemonResDto.abilities().forEach(abilityWrapper -> {
            String abilityName = abilityWrapper.ability().name();
            Abilities ability = abilitiesRepository.findByName(abilityName)
                    .orElseGet(() -> abilitiesRepository.save(Abilities.builder()
                            .name(abilityName)
                            .isHidden(false)
                            .build()));

            RelationAbilities relationAbilities = RelationAbilities.builder()
                    .pokemon(savedPokemon)
                    .ability(ability)
                    .build();

            relationAbilitiesRepository.save(relationAbilities);
        });

        return savedPokemon;
    }

    private String downloadImage(String imageUrl, String pokemonName) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return null;
        }

        String uploadDir = "src/main/resources/static/images/";
        try {
            String fileName = pokemonName + ".png";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream in = new URL(imageUrl).openStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
                return "/images/" + fileName;
            }
        } catch (IOException e) {
            log.error("Failed to download image for pokemon: {}", pokemonName, e);
            return imageUrl; // Fallback to original URL if download fails
        }
    }

    @Transactional
    public List<Pokemon> saveAllPokemon(int limit, int offset) {
        PokemonListResDto listResDto = pokemonClient.getPokemonList(limit, offset);
        return listResDto.results().stream()
                .map(result -> savePokemon(result.name()))
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Pokemon> getPokemonsByWeight(Integer type) {
        if (type == null || type == 3) {
            return pokemonRepository.findAllByOrderByWeightDesc();
        }
        return switch (type) {
            case 0 -> pokemonRepository.findByWeightBetweenOrderByWeightDesc(0, 200);
            case 1 -> pokemonRepository.findByWeightBetweenOrderByWeightDesc(201, 300);
            case 2 -> pokemonRepository.findByWeightGreaterThanOrderByWeightDesc(300);
            default -> pokemonRepository.findAllByOrderByWeightDesc();
        };
    }
}
