package org.example.pokeapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "mst_pokemons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "base_experience")
    private Integer baseExperience;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "image_path")
    private String imagePath;
}
