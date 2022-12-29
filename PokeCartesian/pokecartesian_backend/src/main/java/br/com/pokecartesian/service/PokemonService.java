package br.com.pokecartesian.service;

import br.com.pokecartesian.dto.PokemonDTO;
import br.com.pokecartesian.dto.ResponseDTO;
import br.com.pokecartesian.exception.EmptyListPokemonException;
import br.com.pokecartesian.model.Pokemon;
import br.com.pokecartesian.repository.PokemonRepository;
import br.com.pokecartesian.util.PointUtil;
import org.springframework.stereotype.Service;

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

    public  Pokemon[] cartesianPlane() {
        repository.findAll().forEach(pokemon -> {
            pokemon.setCoordinateX(0);
            pokemon.setCoordinateY(0);
        });
        List<Pokemon> pokemonList = new ArrayList<>();
        Random gerador = new Random();

        for (int i = 0; i < 10; i++) {
            Optional<Pokemon> optPokemon = repository.findById((long) gerador.nextInt(151));
            pokemonList.add(optPokemon.get());
        }

        for (int i = 0; i < 10; i++) {
            pokemonList.forEach(pokemon -> {
                pokemon.setCoordinateX(gerador.nextInt(15));
                pokemon.setCoordinateY(gerador.nextInt(15));

            });
        }

        repository.saveAll(pokemonList);

        Pokemon[] pokemonsList = new Pokemon[225];

        pokemonList.forEach(pokemon -> {
            Integer coordinateX = pokemon.getCoordinateX();
            Integer coordinateY = pokemon.getCoordinateY();

            int position = 210 + coordinateX - (15 * coordinateY);
            pokemonsList[position] = pokemon;
        });

        return pokemonsList;
    }

    public ResponseDTO closestPokemon() {
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonUsedList = new ArrayList<>();
        List<Pokemon> pokemonList = repository.findAll();
        pokemonList.forEach(pokemon -> {
            if(pokemon.getCoordinateX() != 0 || pokemon.getCoordinateY() != 0){
                pokemonDTOList.add(new PokemonDTO(pokemon));
                pokemonUsedList.add(pokemon);
            }

        });
        if (pokemonDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokémon's cadastrados!");
        }

        Pokemon[] pokemonArray = new Pokemon[10];
        pokemonUsedList.toArray(pokemonArray);
        DecimalFormat df = new DecimalFormat("#.######");

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setResposta("A menor distância encontrada é: " +
                df.format(PointUtil.closest(pokemonArray, pokemonArray.length)));

        return responseDTO;
    }
}
