package twenty;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {

    private final List<Integer> jolts;

    public AdapterArray(Stream<String> input) {
        jolts = input.mapToInt(Integer::parseInt).filter(i -> i > 0).sorted().boxed().collect(Collectors.toList());
    }

    /**
     * https://adventofcode.com/2020/day/10
     */
    public int joltage() {
        int oneCount = 0;
        int threeCount = 0;
        if (jolts.get(0) == 1) {
            oneCount++;
        } else {
            threeCount++;
        }
        for (int i = 0; i < jolts.size() - 1; i++) {
            if (jolts.get(i + 1) - jolts.get(i) == 1) {
                oneCount++;
            } else {
                threeCount++;
            }
        }
        return oneCount * (threeCount + 1);
    }

    /**
     * https://adventofcode.com/2020/day/10#part2
     */
    public long permutations() {
        long numPermutations = 1;
        int start = 0;
        int end = start;
        if (jolts.get(0) == 1) {
            start = -1;
        }
        for (int i = 1; i < jolts.size(); i++) {
            if (jolts.get(i) - jolts.get(i - 1) != 1) {
                int numSeries = end - start + 1;
                numPermutations *= calculatePermutations(numSeries);
                start = i;
            }
            end++;
        }
        return numPermutations * calculatePermutations(end - start + 1);
    }

    private int calculatePermutations(int num) {
        switch (num) {
            case 3:
                return 2;
            case 4:
                return 4;
            case 5:
                return 7;
            case 6:
                return 13;
            default:
                return 1;
        }
    }
}
