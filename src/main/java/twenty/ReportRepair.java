package twenty;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportRepair {

    private final Set<Integer> expenses;

    public ReportRepair(Stream<String> input) {
        expenses = input.mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());
    }

    /**
     * https://adventofcode.com/2020/day/1.
     * This fails if the input has only one 1010.
     */
    public int twoSum(final int total) {
        for (Integer candidate: expenses) {
            int conjugate = total - candidate;
            if (expenses.contains(conjugate)) {
                return candidate * conjugate;
            }
        }
        return 0;
    }

    /** https://adventofcode.com/2020/day/1#part2 */
    public long threeSum2020() {
        for (Integer candidateA: expenses) {
            for (Integer candidateB: expenses) {
                int conjugate = 2020 - (candidateA + candidateB);
                if (conjugate <= 0) {
                    continue;
                }
                if (expenses.contains(conjugate)) {
                    return candidateA * candidateB * conjugate;
                }
            }
        }
        return 0;
    }
}
