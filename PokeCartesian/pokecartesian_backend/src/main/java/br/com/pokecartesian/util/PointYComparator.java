package br.com.pokecartesian.util;

import br.com.pokecartesian.model.Pokemon;

import java.util.Comparator;

public class PointYComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon pokemonA, Pokemon pokemonB) {
        return Integer.compare(pokemonA.getCoordinateY(), pokemonB.getCoordinateY());
    }
}
