package twenty;

import java.util.List;

/** https://adventofcode.com/2020/day/13 */
public class ShuttleSearch {

    private final int timestamp;

    private final int[] busIds;

    public ShuttleSearch(List<String> input) {
        timestamp = Integer.parseInt(input.get(0));
        String[] buses = input.get(1).split(",");
        busIds = new int[buses.length];
        for (int i = 0; i < buses.length; i++) {
            if (!buses[i].equals("x")) {
                busIds[i] = Integer.parseInt(buses[i]);
            } else {
                busIds[i] = -1;
            }
        }
    }

    public int prodIdAndDelay() {
        int minDelay = Integer.MAX_VALUE;
        int busId = -1;
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i] == -1) {
                continue;
            }
            int delay = timestamp % busIds[i];
            if (delay == 0) {
                return 0;
            }
            if (busIds[i] - delay < minDelay) {
                minDelay = busIds[i] - delay;
                busId = busIds[i];
            }
        }
        return busId * minDelay;
    }

    /**
     * https://www.reddit.com/r/adventofcode/comments/kc5bl5/weird_math_trick_goes_viral/
     */
    public long bruteForce() {
        int minStep = busIds[0];
        long answer = minStep;
        while (true) {
            int i = 0;
            while (i < busIds.length && (answer + i) % busIds[i] == 0) {
                i++;
            }
            if (i == busIds.length) {
                break;
            }
            answer += minStep;
        }
        return answer;
    }

    /**
     * https://en.wikipedia.org/wiki/Chinese_remainder_theorem#Search_by_sieving
     */
    public long searchBySieving() {
        int step = busIds[0];
        long answer = step;
        for (int i = 1; i < busIds.length; i++) {
            if (busIds[i] == -1) {
                continue;
            }
            while (answer % busIds[i] != busIds[i] - i) {
                answer += step;
            }
            step = step * busIds[i];
        }
        return answer;
    }

    /**
     * http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/chinese_remainder.pdf
     */
    public long crt() {
        long product = 1;
        long sum = 0;
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i] == -1) {
                continue;
            }
            product *= busIds[i];
        }
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i] == -1) {
                continue;
            }
            long z = product / busIds[i];
            sum += i * inverseModulo(z, busIds[i]) * z;
        }
        return product - sum % product;
    }

    private long inverseModulo(long x, long y) {
        if (x != 0) {
            long modulo = y % x;
            return modulo == 0 ? 1 : y - inverseModulo(modulo, x) * y / x;
        }
        return 0;
    }
}
