package org.example.pokeapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonReqDto {

    private String name;
    private Integer baseExperience;
    private Integer weight;
    private String imagePath;
}
