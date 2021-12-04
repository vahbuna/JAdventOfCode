package twenty;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.Grid;

/**
 * https://adventofcode.com/2020/day/11
 */
public final class SeatingSystem extends Grid {

    public static final char OCCUPIED = '#';

    public static final char FLOOR = '.';

    public static final char EMPTY = 'L';

    public SeatingSystem(List<String> seatLayout, Map<Character, Integer> mapping) {
        super(seatLayout, mapping);
    }

    @Override
    public void update() {
        int[][] newState = new int[getNumRows()][];
        for (int i = 0; i < getNumRows(); i++) {
            newState[i] = new int[getNumColumns()];
        }

        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (getStatus(i, j) == EMPTY && countNeighboursWithStatus(i, j, OCCUPIED) == 0) {
                    newState[i][j] = getStatusMapping(OCCUPIED);
                } else if (getStatus(i, j) == OCCUPIED && countNeighboursWithStatus(i, j, OCCUPIED) > 3) {
                    newState[i][j] = getStatusMapping(EMPTY);
                } else {
                    newState[i][j] = getStatusMapping(getStatus(i, j));
                }
            }
        }
        copyStatus(newState);
    }

    public void updateVisible() {
        int[][] newState = new int[getNumRows()][];
        for (int i = 0; i < getNumRows(); i++) {
            newState[i] = new int[getNumColumns()];
        }

        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (getStatus(i, j) == EMPTY && countVisibleOccupied(i, j) == 0) {
                    newState[i][j] = getStatusMapping(OCCUPIED);
                } else if (getStatus(i, j) == OCCUPIED && countVisibleOccupied(i, j) > 4) {
                    newState[i][j] = getStatusMapping(EMPTY);
                } else {
                    newState[i][j] = getStatusMapping(getStatus(i, j));
                }
            }
        }
        copyStatus(newState);
    }

    private int countVisibleOccupied(int row, int column) {
        int count = 0;
        int newRow = row - 1;
        while (newRow >= 0 && getStatus(newRow, column) == FLOOR) {
            newRow--;
        }
        if (newRow >= 0 && getStatus(newRow, column) == OCCUPIED) {
            count++;
        }

        newRow = row - 1;
        int newCol = column - 1;
        while (newRow >= 0 && newCol >= 0 && getStatus(newRow, newCol) == FLOOR) {
            newRow--;
            newCol--;
        }

        if (newRow >= 0 && newCol >= 0 && getStatus(newRow, newCol) == OCCUPIED) {
            count++;
        }

        newRow = row + 1;
        for (; newRow < getNumRows(); newRow++) {
            if (getStatus(newRow, column) != FLOOR) {
                if (getStatus(newRow, column) == OCCUPIED) {
                    count++;
                }
                break;
            }
        }

        newRow = row + 1;
        newCol = column + 1;
        for (; newRow < getNumRows() && newCol < getNumColumns(); newRow++, newCol++) {
            if (getStatus(newRow, newCol) != FLOOR) {
                if (Character.compare(getStatus(newRow, newCol), OCCUPIED) == 0) {
                    count++;
                }
                break;
            }
        }

        newCol = column - 1;
        for (; newCol >= 0; newCol--) {
            if (getStatus(row, newCol) != FLOOR) {
                if (Character.compare(getStatus(row, newCol), OCCUPIED) == 0) {
                    count++;
                }
                break;
            }
        }

        newRow = row + 1;
        newCol = column - 1;
        for (; newRow < getNumRows() && newCol >= 0; newRow++, newCol--) {
            if (getStatus(newRow, newCol) != FLOOR) {
                if (Character.compare(getStatus(newRow, newCol), OCCUPIED) == 0) {
                    count++;
                }
                break;
            }
        }

        newCol = column + 1;
        for (; newCol < getNumColumns(); newCol++) {
            if (getStatus(row, newCol) != FLOOR) {
                if (Character.compare(getStatus(row, newCol), OCCUPIED) == 0) {
                    count++;
                }
                break;
            }
        }

        newRow = row - 1;
        newCol = column + 1;
        for (; newRow >= 0 && newCol < getNumColumns(); newRow--, newCol++) {
            if (getStatus(newRow, newCol) != FLOOR) {
                if (Character.compare(getStatus(newRow, newCol), OCCUPIED) == 0) {
                    count++;
                }
                break;
            }
        }

        return count;
    }

    public int stableSeats() {
        while (true) {
            int[][] previousState = getGrid();
            update();
            if (Arrays.deepEquals(previousState, getGrid())) {
                break;
            }
        }
        return countStatus(OCCUPIED);
    }

    public int stableVisibleSeats() {
        while (true) {
            int[][] previousState = getGrid();
            updateVisible();
            if (Arrays.deepEquals(previousState, getGrid())) {
                break;
            }
        }
        return countStatus(OCCUPIED);
    }
}
