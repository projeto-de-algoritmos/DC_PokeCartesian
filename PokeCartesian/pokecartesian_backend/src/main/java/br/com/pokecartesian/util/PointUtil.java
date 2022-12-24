package br.com.pokecartesian.util;

import br.com.pokecartesian.model.Pokemon;

import java.util.Arrays;


public class PointUtil {
        public static float dist(Pokemon p1, Pokemon p2) {
            return (float) Math.sqrt((p1.getCoordinateX() - p2.getCoordinateX()) * (p1.getCoordinateX() - p2.getCoordinateX()) +
                    (p1.getCoordinateY() - p2.getCoordinateY()) * (p1.getCoordinateY() - p2.getCoordinateY())
            );
        }

        public static float bruteForce(Pokemon[] pokemon, int n) {
            float min = Float.MAX_VALUE;
            float currMin = 0;

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    currMin = dist(pokemon[i], pokemon[j]);
                    if (currMin < min) {
                        min = currMin;
                    }
                }
            }
            return min;
        }

        public static float stripClosest(Pokemon[] strip, int size, float d) {
            float min = d;

            Arrays.sort(strip, 0, size, new PointYComparator());
            for (int i = 0; i < size; ++i) {
                for (int j = i + 1; j < size && (strip[j].getCoordinateY() - strip[i].getCoordinateY()) < min; ++j) {
                    if (dist(strip[i], strip[j]) < min) {
                        min = dist(strip[i], strip[j]);
                    }
                }
            }

            return min;
        }

        public static float closestUtil(Pokemon[] pokemon,
                                 int startIndex,
                                 int endIndex) {

            if ((endIndex - startIndex) <= 3) {
                return bruteForce(pokemon, endIndex);
            }

            int mid = startIndex + (endIndex - startIndex) / 2;
            Pokemon midPoint = pokemon[mid];

            float dl = closestUtil(pokemon, startIndex, mid);
            float dr = closestUtil(pokemon, mid, endIndex);

            float d = Math.min(dl, dr);

            Pokemon[] strip = new Pokemon[endIndex];
            int j = 0;
            for (int i = 0; i < endIndex; i++) {
                if (Math.abs(pokemon[i].getCoordinateX() - midPoint.getCoordinateX()) < d) {
                    strip[j] = pokemon[i];
                    j++;
                }
            }

            return Math.min(d, stripClosest(strip, j, d));
        }

        public static float closest(Pokemon[] pokemon, int n) {

            Arrays.sort(pokemon, 0, n, new PointXComparator());

            return closestUtil(pokemon, 0, n);
        }
    }