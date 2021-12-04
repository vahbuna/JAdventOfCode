package fifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ReindeerOlympics {

    private final List<Reindeer> participants = new ArrayList<>();

    public static class Reindeer {

        private final String name;

        private final int speed;

        private final int flyDuration;

        private final int restDuration;

        public Reindeer(final String rName, final int rSpeed, final int rFlyTime, final int rRestTime) {
            name = rName;
            speed = rSpeed;
            flyDuration = rFlyTime;
            restDuration = rRestTime;
        }

        public int computeDistance(final int numSeconds) {
            int multiplier = numSeconds / (flyDuration + restDuration);
            int residualTime = numSeconds % (flyDuration + restDuration);
            int distance = speed * flyDuration * multiplier;
            if (residualTime >= flyDuration) {
                distance += speed * flyDuration;
            } else {
                distance += speed * residualTime;
            }
            return distance;
        }
    }

    public ReindeerOlympics(Stream<String> info) {
        info.forEach(
                rInfo -> {
                    String[] fields = rInfo.split(" ");
                    participants.add(new Reindeer(fields[0], Integer.parseInt(fields[3]), Integer.parseInt(fields[6]),
                            Integer.parseInt(fields[13])));
                }
        );
    }

    /**
     * https://adventofcode.com/2015/day/14
     */
    public int firstScoring(int numSeconds) {
        int maxDistance = -1;
        for (Reindeer reindeer : participants) {
            int distance = reindeer.computeDistance(numSeconds);
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }

    /**
     * https://adventofcode.com/2015/day/14#part2
     */
    public int secondScoring(int numSeconds) {
        int[] distances = new int[participants.size()];
        Integer[] points = new Integer[participants.size()];
        for (int i = 0; i < participants.size(); i++) {
            points[i] = 0;
        }
        for (int i = 1; i < numSeconds + 1; i++) {
            int maxDistance = -1;
            int reindeerIdx = 0;
            for (Reindeer reindeer : participants) {
                int distance = reindeer.computeDistance(i);
                distances[reindeerIdx] = distance;
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
                reindeerIdx++;
            }

            for (int idx = 0; idx < participants.size(); idx++) {
                if (distances[idx] == maxDistance) {
                    points[idx] += 1;
                }
            }
        }
        return Collections.max(Arrays.asList(points));
    }
}
