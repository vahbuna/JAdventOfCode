package fifteen;

import java.util.List;
import java.util.Map;

import common.Grid;

public class LikeAGifForYourYard extends Grid {

    public static final char ON = '#';

    public static final char OFF = '.';

    private boolean cornersAlwaysOn = false;

    public LikeAGifForYourYard(List<String> input, Map<Character, Integer> mapping) {
        super(input, mapping);
    }

    // https://adventofcode.com/2015/day/18
    public int numLightsOn(final int steps) {
        for (int i = 0; i < steps; i++) {
            update();
        }
        return countStatus(ON);
    }

    // https://adventofcode.com/2015/day/18#part2
    public int numLightsOnConway(final int steps) {
        cornersAlwaysOn = true;
        for (int i = 0; i < steps; i++) {
            update();
        }
        cornersAlwaysOn = false;
        return countStatus(ON);
    }

    @Override
    public void update() {
        int[][] newState = new int[getNumRows()][];
        for (int i = 0; i < getNumRows(); i++) {
            newState[i] = new int[getNumColumns()];
        }

        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (cornersAlwaysOn && ((i == 0 && j == 0)
                        || (i == getNumRows() - 1 && j == 0)
                        || (i == 0 && j == getNumColumns() - 1)
                        || (i == getNumRows() - 1 && j == getNumColumns() - 1))
                ) {
                    newState[i][j] = getStatusMapping(ON);
                    continue;
                }
                int neighboursOn = countNeighboursWithStatus(i, j, ON);
                if ((getStatus(i, j) == ON && (neighboursOn == 2 || neighboursOn == 3))
                        || (getStatus(i, j) == OFF && neighboursOn == 3)) {
                    newState[i][j] = getStatusMapping(ON);
                }
            }
        }
        copyStatus(newState);
    }
}
