package twenty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class HandheldHalting {
    private final List<String> instructions;
    private static final int CONNECTED_TO_START = 1;
    private static final int CONNECTED_TO_END = 2;
    private static int accumulator = 0;

    public HandheldHalting(Stream<String> input) {
        instructions = input.collect(Collectors.toList());
    }

    public int accValBeforeLoop() {
        int step;
        Set<Integer> seenIndices = new HashSet<>();
        for (int i = 0; i < instructions.size(); i += step) {
            if (seenIndices.contains(i)) {
                break;
            } else {
                seenIndices.add(i);
            }
            step = execute(instructions.get(i));
        }
        return accumulator;
    }

    public void repair() {
        final int V = instructions.size();
        List<Integer>[] adj = new ArrayList[V];
        int[] marked = new int[V];

        buildAdjacencyList(adj, instructions);
        markConnectedToStart(marked, adj);
        markConnectedToEnd(V, marked, adj);

        for (int i = 0; i < V; i++) {
            if (marked[i] == 1) {
                String instruction = instructions.get(i);
                String [] fields = instruction.split(" ");
                if ("nop".equals(fields[0]) && marked[i + Integer.parseInt(fields[1])] == CONNECTED_TO_END) {
                    instructions.set(i, instruction.replace("nop", "jmp"));
                    break;
                } else if ("jmp".equals(fields[0]) && marked[i + 1] == CONNECTED_TO_END) {
                    instructions.set(i, instruction.replace("jmp", "nop"));
                    break;
                }
            }
        }
    }

    public int accVal() {
        int step;
        for (int i = 0; i < instructions.size(); i += step) {
            step = execute(instructions.get(i));
        }
        return accumulator;
    }

    private void buildAdjacencyList(List<Integer>[] adj, final List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            String [] fields = instruction.split(" ");
            int target = i + 1;
            if ("jmp".equals(fields[0])) {
                target = i + Integer.parseInt(fields[1]);
            }
            adj[i].add(target);
        }
    }

    private void markConnectedToStart(int[] marked, final List<Integer>[] adj) {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(0);
        while (!nodes.isEmpty()) {
            int node = nodes.remove(0);
            if (marked[node] == 1) {
                break;
            }
            marked[node] = CONNECTED_TO_START;
            nodes.addAll(adj[node]);
        }
    }

    private void markConnectedToEnd(int end, int [] marked, final List<Integer>[] adj) {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(end);

        while (!nodes.isEmpty()) {
            int node = nodes.remove(0);
            for (int i = 0; i < adj.length; i++) {
                if (adj[i].contains(node)) {
                    nodes.add(i);
                    marked[i] = CONNECTED_TO_END;
                }
            }
        }
    }

    private static int execute(String instruction) {
        int step = 1;
        if (instruction.startsWith("nop")) {
            return step;
        }
        String [] fields = instruction.split(" ");
        int val = Integer.parseInt(fields[1]);
        if ("acc".equals(fields[0])) {
            accumulator += val;
        } else if ("jmp".equals(fields[0])) {
            step = val;
        }
        return step;
    }

    public static void resetAccumulator() {
        accumulator = 0;
    }

}
