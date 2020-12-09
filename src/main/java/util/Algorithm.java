package util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algorithm {
    private Algorithm() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static double bellmanHeldKarpTravellingSalesman(final double[][] graph, DoubleBinaryOperator comp,
                                                           final double reference) {
        double distance = reference;
        for (int start = 0; start < graph.length; start++) {
            distance = comp.applyAsDouble(distance, findOptimalPath(graph, start, comp, reference));
        }
        return distance;
    }

    /** https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm#Pseudocode[5] */
    private static double findOptimalPath(final double[][] graph, int start, DoubleBinaryOperator comp,
                                          final double reference) {
        Map<TspRoute, Double> costs = new HashMap<>();
        List<Integer> nodes = IntStream.range(0, graph.length).boxed().collect(Collectors.toList());
        nodes.remove(start);
        for (int i: nodes) {
            Set<Integer> init = new HashSet<>();
            init.add(i);
            costs.put(new TspRoute(init, i), graph[i][start]);
        }

        for (int i = 2; i <= nodes.size(); i++) {
            for (Set<Integer> s : allSubsets(nodes)) {
                if (s.size() != i) {
                    continue;
                }
                for (int k : s) {
                    Set<Integer> newSet = new HashSet<>(s);
                    newSet.remove(k);
                    double minCost = reference;
                    for (int elem : newSet) {
                        minCost = comp.applyAsDouble(costs.get(new TspRoute(newSet, elem)) + graph[k][elem], minCost);
                    }
                    costs.put(new TspRoute(s, k), minCost);
                }
            }
        }
        List<Double> routeCosts =  costs.entrySet().stream()
                .filter(elem -> elem.getKey().nodeSet.size() == nodes.size())
                .mapToDouble(Map.Entry::getValue)
                .boxed()
                .collect(Collectors.toList());
        double answer = Collections.max(routeCosts);
        if (Double.compare(reference, Double.POSITIVE_INFINITY) == 0) {
            answer = Collections.min(routeCosts);
        }
        return answer;
    }

    public static <T extends Object> Set<Set<T>> allSubsets(List<T> objectList) {
        if (objectList.isEmpty()) {
            return  new HashSet<>();
        }
        Set<Set<T>> setOfSets = new HashSet<>();
        setOfSets.add(new HashSet<>(objectList));
        setOfSets.add(new HashSet<>(objectList.subList(0, 1)));
        for (Set<T> a : allSubsets(objectList.subList( 1,  objectList.size()))) {
            setOfSets.add(a);
            HashSet<T> c = new HashSet<>(objectList.subList(0, 1));
            c.addAll(a);
            setOfSets.add(c);
        }
        return setOfSets;
    }

    private static class TspRoute {
        private Set<Integer> nodeSet;
        private int  nodeK;

        public TspRoute(Set<Integer> set, int k) {
            nodeSet = set;
            nodeK = k;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final TspRoute other = (TspRoute) obj;
            return (nodeSet.equals(other.nodeSet) && nodeK == other.nodeK) ;
        }

        @Override
        public String toString() {
            return "from start to " + nodeK + " via " + nodeSet.toString();
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
    }
}
