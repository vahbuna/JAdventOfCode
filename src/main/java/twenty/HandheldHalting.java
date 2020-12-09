package twenty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class HandheldHalting {
    private static int accumulator = 0;
    private final List<String> instructions;
    private Set<Integer> seenIndices = new HashSet<>();

    public HandheldHalting(Stream<String> input) {
        instructions = input.collect(Collectors.toList());
    }

    public int accValBeforeLoop() {
        int step = 1;
        for (int i = 0; i < instructions.size(); i += step) {
            if (seenIndices.contains(i)) {
                break;
            } else {
                seenIndices.add(i);
            }
            step = 1;
            String instruction = instructions.get(i);
            if (instruction.startsWith("nop")) {
                continue;
            }
            String [] fields = instruction.split(" ");
            int val = Integer.parseInt(fields[1]);
            if ("acc".equals(fields[0])) {
                accumulator += val;
            } else if ("jmp".equals(fields[0])) {
                step = val;
            }
        }
        return accumulator;
    }

    public int accValRepaired() {
        int step = 1;
        int retries = 0;
        int previousJmp = -1;
        for (int i = 0; i < instructions.size(); i += step) {
            if (seenIndices.contains(i) && previousJmp == -1) {
                // came here because of a jump so solution is to make it a nop
                step = Math.abs(step) + 1;
                previousJmp = i + step - 1;
                continue;
            } if (seenIndices.contains(i) && previousJmp != -1 && retries == 0) {
                // changing jump to nop didn't work lets find a positive nop
                // before the corrected jump
                String newInstruction = instructions.get(--previousJmp);
                while (!newInstruction.startsWith("nop +")) {
                    newInstruction = instructions.get(--previousJmp);
                }
                String [] fields = newInstruction.split(" ");
                instructions.set(previousJmp, "jmp "+fields[1]);
                System.out.println(i - previousJmp);
                System.out.println(previousJmp);
                System.out.println(newInstruction);
                step = previousJmp - i;
                retries++;
                continue;
            } else {
                seenIndices.add(i);
            }
            step = 1;
            String instruction = instructions.get(i);
            if (instruction.startsWith("nop")) {
                continue;
            }
            String [] fields = instruction.split(" ");
            int val = Integer.parseInt(fields[1]);
            if ("acc".equals(fields[0])) {
                accumulator += val;
            } else if ("jmp".equals(fields[0])) {
                step = val;
            }
        }
        return accumulator;
    }

}
