package fifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

import util.Algorithm;

/**
 * https://adventofcode.com/2015/day/13
 */
public class KnightsOfTheDinnerTable {

    private final Map<String, Integer> guestToInt = new HashMap<>();

    private final List<String> guests = new ArrayList<>();

    private final double[][] happinessGraph;

    private final List<Happiness> happinessList = new ArrayList<>();

    static class Happiness {

        private final String source;

        private final String destination;

        private final double happinessUnits;

        public Happiness(final String src, final String dest, final double units) {
            source = src;
            destination = dest;
            happinessUnits = units;
        }

        @Override
        public String toString() {
            return source + " has " + happinessUnits + " units by sitting next to " + destination;
        }
    }

    public KnightsOfTheDinnerTable(Stream<String> inputDistances, boolean addMe) {
        inputDistances.forEach(
                distance -> {
                    String[] fields = distance.split(" ");
                    int numFields = fields.length;
                    String source = fields[0];
                    String destination = fields[numFields - 1].substring(0, fields[numFields - 1].length() - 1);
                    double happiness = Double.parseDouble(fields[3]);
                    if (fields[2].equals("lose")) {
                        happiness *= -1;
                    }
                    happinessList.add(new Happiness(source, destination, happiness));
                    int idx = guests.size();
                    if (!guestToInt.containsKey(source)) {
                        guestToInt.put(source, idx);
                        guests.add(source);
                    }

                    idx = guests.size();
                    if (!guestToInt.containsKey(destination)) {
                        guestToInt.put(destination, idx);
                        guests.add(destination);
                    }
                }
        );
        int nodes = guests.size();
        if (addMe) {
            guestToInt.put("Me", nodes);
            nodes++;
        }
        happinessGraph = new double[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            Arrays.fill(happinessGraph[i], Double.MIN_VALUE);
        }

        happinessList.forEach(d -> {
            int i = guestToInt.get(d.source);
            int j = guestToInt.get(d.destination);
            double val = d.happinessUnits;
            happinessGraph[i][j] = val;
        });

        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                happinessGraph[i][j] = happinessGraph[i][j] + happinessGraph[j][i];
                happinessGraph[j][i] = happinessGraph[i][j];
            }
        }

        if (addMe) {
            for (int i = 0; i < nodes - 1; i++) {
                happinessGraph[i][nodes - 1] = 0;
                happinessGraph[nodes - 1][i] = 0;
            }
        }
    }

    public double maxHappiness() {
        DoubleBinaryOperator maxHappiness = Math::max;
        return Algorithm.bellmanHeldKarpTravellingSalesman(happinessGraph, maxHappiness, Double.NEGATIVE_INFINITY, true);
    }
}
