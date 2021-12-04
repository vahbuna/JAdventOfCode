package fifteen;

import static util.Functions.padToN;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoSuchThingAsTooMuch {

    private final int eggnog;

    private final List<Integer> containers;

    private final Map<Integer, Integer> containerCount = new HashMap<>();

    public NoSuchThingAsTooMuch(List<Integer> inputContainers, int eggnogCapacity) {
        eggnog = eggnogCapacity;
        containers = inputContainers;
    }

    // Brute force, may be Linear Diophantine Equation is better ?
    // https://adventofcode.com/2015/day/17
    public int combinationOfContainers() {
        int answer = 0;
        for (long i = 1; i <= Math.pow(2.0, containers.size()); i++) {
            int sum = 0;
            int numContainers = 0;
            String binary = padToN(Long.toBinaryString(i), containers.size());
            for (int idx = 0; idx < containers.size(); idx++) {
                int isContainerPresent = binary.charAt(idx) - 48;
                sum += containers.get(idx) * isContainerPresent;
                if (isContainerPresent == 1) {
                    numContainers++;
                }
            }

            if (sum == eggnog) {
                answer++;
                int presentCount = containerCount.getOrDefault(numContainers, 0);
                containerCount.put(numContainers, presentCount + 1);
            }
        }
        return answer;
    }

    /**
     * https://adventofcode.com/2015/day/17#part2
     */
    public int combinationOfMinimumContainers() {
        combinationOfContainers();
        int minimumContainers = Collections.min(containerCount.keySet());
        return containerCount.get(minimumContainers);
    }
}
