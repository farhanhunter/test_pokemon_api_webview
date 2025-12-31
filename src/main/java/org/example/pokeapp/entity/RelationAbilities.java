package org.example.pokeapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "mst_pokemon_abilities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationAbilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Abilities ability;
}
