package fifteen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ProbablyaFireHazard {

    private static final int SIZE = 1000;
    private final int[][] grid;
    private static final String TOGGLE = "toggle";

    public ProbablyaFireHazard() {
        grid = new int[SIZE][SIZE];
    }

    public void follow(String instruction) {
        int state = 0;
        if (instruction.startsWith("turn on")) {
            state = 1;
        }
        String[] keywords = instruction.split(" ");
        if (instruction.startsWith(TOGGLE)) {
            List<Integer> start = getIndices(keywords[1]);
            List<Integer> end = getIndices(keywords[3]);
            for (int row = start.get(0); row <=  end.get(0); row++) {
                for (int col = start.get(1); col <= end.get(1); col++) {
                    grid[row][col] = 1 - grid[row][col];
                }
            }
        } else {
            List<Integer> start = getIndices(keywords[2]);
            List<Integer> end = getIndices(keywords[4]);
            for (int row = start.get(0); row <=  end.get(0); row++) {
                for (int col = start.get(1); col <= end.get(1); col++) {
                    grid[row][col] = state;
                }
            }
        }
    }

    public int litCount() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void followBrightness(String instruction) {
        int state = -1;
        if (instruction.startsWith("turn on")) {
            state = 1;
        } else if (instruction.startsWith(TOGGLE)) {
            state = 2;
        }
        List<Integer> start;
        List<Integer> end;
        String[] keywords = instruction.split(" ");
        if (instruction.startsWith(TOGGLE)) {
            start = getIndices(keywords[1]);
            end = getIndices(keywords[3]);
        } else {
            start = getIndices(keywords[2]);
            end = getIndices(keywords[4]);
        }
        for (int row = start.get(0); row <=  end.get(0); row++) {
            for (int col = start.get(1); col <= end.get(1); col++) {
                grid[row][col] += state;
                if (grid[row][col] < 0) {
                    grid[row][col] = 0;
                }
            }
        }
    }

    public int totalBrightness() {
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                total += grid[i][j];
            }
        }
        return total;
    }

    private List<Integer> getIndices(String indexPair) {
        return Arrays.stream(indexPair.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
