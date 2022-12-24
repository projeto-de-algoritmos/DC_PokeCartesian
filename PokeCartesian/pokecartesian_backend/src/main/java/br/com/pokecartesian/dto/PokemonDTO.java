package br.com.pokecartesian.dto;

import br.com.pokecartesian.model.Pokemon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PokemonDTO {
    private Long id;
    private String name;
    private String url;
    private Integer coordinateX;
    private Integer coordinateY;

    public PokemonDTO(Pokemon pokemon){
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.url = pokemon.getUrl();
        this.coordinateX = pokemon.getCoordinateX();
        this.coordinateY = pokemon.getCoordinateY();
    }
}

