package fifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

import util.Algorithm;

public class AllinaSingleNight {

    private final Map<String, Integer> cityToInt = new HashMap<>();
    private final List<String> cities = new ArrayList<>();
    private final double[][] distanceGraph;
    private final List<Distance> distances = new ArrayList<>();

    static class Distance {
        private final String source;
        private final String destination;
        private final double length;

        public Distance(final String src, final String dest, final double len) {
            source = src;
            destination = dest;
            length = len;
        }
    }

    public AllinaSingleNight(Stream<String> inputDistances) {
        inputDistances.forEach(
                distance -> {
                    String[] fields = distance.split(" ");
                    String source = fields[0];
                    String destination = fields[2];
                    double length = Double.parseDouble(fields[4]);
                    distances.add(new Distance(source, destination, length));
                    int idx = cities.size();
                    if (!cityToInt.containsKey(source)) {
                        cityToInt.put(source, idx);
                        cities.add(source);
                    }

                    idx = cities.size();
                    if (!cityToInt.containsKey(destination)) {
                        cityToInt.put(destination, idx);
                        cities.add(destination);
                    }
                }
        );
        int nodes = cities.size();
        distanceGraph = new double[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            Arrays.fill(distanceGraph[i], Double.MAX_VALUE);
            distanceGraph[i][i] = 0.0;
        }

        distances.forEach(d -> {
            int i = cityToInt.get(d.source);
            int j = cityToInt.get(d.destination);
            double val = d.length;
            distanceGraph[i][j] = val;
            distanceGraph[j][i] = val;
        });
    }

    public double shortestRoute() {
        DoubleBinaryOperator minDist = Math::min;
        return Algorithm.bellmanHeldKarpTravellingSalesman(distanceGraph, minDist, Double.POSITIVE_INFINITY);
    }

    public double longestDistance() {
        DoubleBinaryOperator minDist = Math::max;
        return Algorithm.bellmanHeldKarpTravellingSalesman(distanceGraph, minDist, Double.NEGATIVE_INFINITY);
    }
}
