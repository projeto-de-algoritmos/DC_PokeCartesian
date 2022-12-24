package br.com.pokecartesian.service;

import br.com.pokecartesian.dto.PokemonDTO;
import br.com.pokecartesian.exception.EmptyListPokemonException;
import br.com.pokecartesian.model.Pokemon;
import br.com.pokecartesian.repository.PokemonRepository;
import br.com.pokecartesian.util.PointUtil;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<PokemonDTO> listAll(){
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonList = repository.findAll();
        pokemonList.forEach(pokemon -> pokemonDTOList.add(new PokemonDTO(pokemon)));
        if (pokemonDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokêmon's cadastrados!");
        }
        return pokemonDTOList;
    }

    //DecimalFormat
    public String closestPokemon() {
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonList = repository.findAll();
        pokemonList.forEach(pokemon -> pokemonDTOList.add(new PokemonDTO(pokemon)));
        if (pokemonDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokêmon's cadastrados!");
        }

        Random gerador = new Random();

        for (int i = 0; i < 150; i++) {
            pokemonList.forEach(pokemon -> {
                pokemon.setCoordinateX(gerador.nextInt(300));
                pokemon.setCoordinateY(gerador.nextInt(300));
                pokemonDTOList.add(new PokemonDTO(pokemon));
            });
        }

        Pokemon[]pokemonArray = new Pokemon[151];
        pokemonList.toArray(pokemonArray);
        DecimalFormat df = new DecimalFormat("#.######");

        return "A menor distância encontrada é: " +
                df.format(PointUtil.closest(pokemonArray, pokemonArray.length));
    }
}
