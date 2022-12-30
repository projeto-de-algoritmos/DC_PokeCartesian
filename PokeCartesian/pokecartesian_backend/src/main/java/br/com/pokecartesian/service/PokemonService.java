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

        for (int i = 1; i <= 10; i++) {
            Optional<Pokemon> optPokemon = repository.findById((long) i);
            pokemonList.add(optPokemon.get());
        }

        pokemonList = setCoordenates(pokemonList);

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

    public List<Pokemon>  setCoordenates(List<Pokemon> pokemonList ){
        Random gerador = new Random();
        pokemonList.forEach(pokemon -> {
            pokemon.setCoordinateX(gerador.nextInt(15));
            pokemon.setCoordinateY(gerador.nextInt(15));
            System.out.println(pokemon.getCoordinateX()+ "," +pokemon.getCoordinateY() + " -> " + pokemon.getName());
        });

        for (int i = 1; i <= 10; i++) {
            int cont = 0;
            Pokemon pokemonAtual = pokemonList.get(i-1);
            for (int j = 0; j <= 9; j++){
                if(pokemonList.get(j).getCoordinateX().equals(pokemonAtual.getCoordinateX()) &&
                        pokemonList.get(j).getCoordinateY().equals(pokemonAtual.getCoordinateY())){
                    cont++;
                }
            }
            if(cont > 1){
                pokemonList = setCoordenates(pokemonList);
                break;
            }
        }

        return pokemonList;
    }

    public ResponseDTO closestPokemon() {
        List<Pokemon> pokemonList = repository.findAll();
        Pokemon[] pokemonArray = new Pokemon[pokemonList.size()];
        pokemonList.toArray(pokemonArray);
        DecimalFormat df = new DecimalFormat("#.######");

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setResposta(df.format(PointUtil.closest(pokemonArray, pokemonArray.length)));

        return responseDTO;
    }
}
