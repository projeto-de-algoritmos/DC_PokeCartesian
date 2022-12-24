package br.com.pokecartesian.util;

import br.com.pokecartesian.model.Pokemon;

import java.util.Comparator;

public class PointXComparator  implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon pokemonA, Pokemon pokemonB) {
        return Integer.compare(pokemonA.getCoordinateX(), pokemonB.getCoordinateX());
    }
}
