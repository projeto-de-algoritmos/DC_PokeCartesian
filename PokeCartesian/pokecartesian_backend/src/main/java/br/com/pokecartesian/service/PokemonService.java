package br.com.pokecartesian.service;

import br.com.pokecartesian.dto.PokemonDTO;
import br.com.pokecartesian.exception.EmptyListPokemonException;
import br.com.pokecartesian.model.Pokemon;
import br.com.pokecartesian.repository.PokemonRepository;
import br.com.pokecartesian.util.PointUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public  Pokemon[][] cartesianPlane() {
        List<Pokemon> pokemonList = new ArrayList<>();
        Random gerador = new Random();

        for (int i = 0; i < 20; i++) {
            Optional<Pokemon> optPokemon = repository.findById((long) gerador.nextInt(151));
            pokemonList.add(optPokemon.get());
        }

        for (int i = 0; i < 20; i++) {
            pokemonList.forEach(pokemon -> {
                pokemon.setCoordinateX(gerador.nextInt(11));
                pokemon.setCoordinateY(gerador.nextInt(11));
            });
        }

        repository.saveAll(pokemonList);

        Long rowLen = 11L, colLen = 11L;
        Pokemon[][] matrix = new Pokemon[11][11];
        for(int i = 0; i < rowLen; i++)
            for (int j = 0; j < colLen; j++)
                matrix[i][j] = null;

        for(int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                pokemonList.forEach(pokemon -> {
                    Integer coordinateX = pokemon.getCoordinateX();
                    Integer coordinateY = pokemon.getCoordinateY();
                    matrix[coordinateX][coordinateY] = pokemon;
                });
            }
        }

        return matrix;
    }

    public String closestPokemon() {
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonList = repository.findAll();
        pokemonList.forEach(pokemon -> pokemonDTOList.add(new PokemonDTO(pokemon)));
        if (pokemonDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokémon's cadastrados!");
        }

        Pokemon[]pokemonArray = new Pokemon[151];
        pokemonList.toArray(pokemonArray);
        DecimalFormat df = new DecimalFormat("#.######");

        return "A menor distância encontrada é: " +
                df.format(PointUtil.closest(pokemonArray, pokemonArray.length));
    }
}
