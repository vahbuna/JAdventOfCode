package fifteen;

import static util.Functions.partition;
import static util.Functions.permutate;

import java.util.ArrayList;
import java.util.List;

public class ScienceForHungryPeople {

    private int[][] properties;

    private final int totalTeaspoons;

    private static final int CALORIE_FIELD = 4;

    public ScienceForHungryPeople(List<String> ingredients, final int totalAmount) {
        int numIngredients = ingredients.size();
        properties = new int[numIngredients][5];
        totalTeaspoons = totalAmount;
        for (int i = 0; i < numIngredients; i++) {
            String[] fields = ingredients.get(i).replaceAll("[^0-9,-]+", "").split(",");
            properties[i][0] = Integer.parseInt(fields[0]);
            properties[i][1] = Integer.parseInt(fields[1]);
            properties[i][2] = Integer.parseInt(fields[2]);
            properties[i][3] = Integer.parseInt(fields[3]);
            properties[i][4] = Integer.parseInt(fields[4]);
        }
    }

    /**
     * https://adventofcode.com/2015/day/15
     */
    public long getCookieScore() {
        return getScore(totalTeaspoons, totalTeaspoons, properties.length, 0, false, 0);
    }

    /**
     * https://adventofcode.com/2015/day/15#part2
     */
    public long getCalorieCookieScore(int calories) {
        return getScore(totalTeaspoons, totalTeaspoons, properties.length, 0, true, calories);
    }

    private long getScore(int n, final int max, final int numPartitions, long maxScore,
                          boolean countCalories, int calories) {

        for (List<Integer> partition : partition(n, max, numPartitions, new ArrayList<>())) {
            for (List<Integer> proportion : permutate(partition)) {
                int[] sums = new int[CALORIE_FIELD + 1];
                for (int j = 0; j <= CALORIE_FIELD; j++) {
                    for (int i = 0; i < numPartitions; i++) {
                        sums[j] += proportion.get(i) * properties[i][j];
                    }
                    sums[j] = Math.max(0, sums[j]);
                }
                if (countCalories && sums[CALORIE_FIELD] != calories) {
                    continue;
                }
                long score = 1;

                for (int j = 0; j < CALORIE_FIELD; j++) {
                    score *= sums[j];
                }
                maxScore = Math.max(score, maxScore);
            }
        }
        return maxScore;
    }
}
