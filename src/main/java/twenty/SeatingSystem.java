package twenty;

import java.util.Arrays;
import java.util.List;

/**
 * https://adventofcode.com/2020/day/11
 */
public final class SeatingSystem {

    private final int[][] seats;

    private final int numRows;

    private final int numColumns;

    public SeatingSystem(List<String> seatLayout) {
        numRows = seatLayout.size();
        numColumns = seatLayout.get(0).length();
        seats = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                char status = seatLayout.get(i).charAt(j);
                if (status == 'L') {
                    seats[i][j] = 1;
                } else if (status == '#') {
                    seats[i][j] = 2;
                }
            }
        }
    }

    public int stableSeats() {
        int[][] newState = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            newState[i] = new int[numColumns];
        }

        while (true) {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (seats[i][j] == 0) {
                        newState[i][j] = 0;
                    } else if (seats[i][j] == 1 && countOccupied(i, j) == 0) {
                        newState[i][j] = 2;
                    } else if (seats[i][j] == 2 && countOccupied(i, j) > 3) {
                        newState[i][j] = 1;
                    }
                }
            }

            if (Arrays.deepEquals(seats, newState)) {
                break;
            }
            for (int i = 0; i < numRows; i++) {
                seats[i] = newState[i].clone();
            }
        }
        return totalOccupied();
    }

    private int totalOccupied() {
        int count = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (seats[i][j] == 2) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countOccupied(int row, int column) {
        int occupied = 0;
        if (row > 0 && seats[row - 1][column] == 2) {
            occupied++;
        }
        if (row > 0 && column > 0 && seats[row - 1][column - 1] == 2) {
            occupied++;
        }
        if (row < numRows - 1 && seats[row + 1][column] == 2) {
            occupied++;
        }
        if (row < numRows - 1 && column < numColumns - 1 && seats[row + 1][column + 1] == 2) {
            occupied++;
        }
        if (column > 0 && seats[row][column - 1] == 2) {
            occupied++;
        }
        if (column < numColumns - 1 && seats[row][column + 1] == 2) {
            occupied++;
        }
        if (row > 0 && column < numColumns - 1 && seats[row - 1][column + 1] == 2) {
            occupied++;
        }
        if (row < numRows - 1 && column > 0 && seats[row + 1][column - 1] == 2) {
            occupied++;
        }
        return occupied;
    }

    public int stableVisibleSeats() {
        int[][] newState = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            newState[i] = new int[numColumns];
        }

        while (true) {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (seats[i][j] == 0) {
                        newState[i][j] = 0;
                    } else if (seats[i][j] == 1 && countVisibleOccupied(i, j) == 0) {
                        newState[i][j] = 2;
                    } else if (seats[i][j] == 2 && countVisibleOccupied(i, j) > 4) {
                        newState[i][j] = 1;
                    }
                }
            }

            if (Arrays.deepEquals(seats, newState)) {
                break;
            }
            for (int i = 0; i < numRows; i++) {
                seats[i] = newState[i].clone();
            }
        }
        return totalOccupied();
    }

    private int countVisibleOccupied(int row, int column) {
        int count = 0;
        int newRow = row - 1;
        while (newRow >= 0 && seats[newRow][column] == 0) {
            newRow--;
        }
        if (newRow >= 0 && seats[newRow][column] == 2) {
            count++;
        }

        newRow = row - 1;
        int newCol = column - 1;
        while (newRow >= 0 && newCol >= 0 && seats[newRow][newCol] == 0) {
            newRow--;
            newCol--;
        }

        if (newRow >= 0 && newCol >= 0 && seats[newRow][newCol] == 2) {
            count++;
        }

        newRow = row + 1;
        for (; newRow < numRows; newRow++) {
            if (seats[newRow][column] > 0) {
                if (seats[newRow][column] == 2) {
                    count++;
                }
                break;
            }
        }

        newRow = row + 1;
        newCol = column + 1;
        for (; newRow < numRows && newCol < numColumns; newRow++, newCol++) {
            if (seats[newRow][newCol] > 0) {
                if (seats[newRow][newCol] == 2) {
                    count++;
                }
                break;
            }
        }

        newCol = column - 1;
        for (; newCol >= 0; newCol--) {
            if (seats[row][newCol] > 0) {
                if (seats[row][newCol] == 2) {
                    count++;
                }
                break;
            }
        }

        newRow = row + 1;
        newCol = column - 1;
        for (; newRow < numRows && newCol >= 0; newRow++, newCol--) {
            if (seats[newRow][newCol] > 0) {
                if (seats[newRow][newCol] == 2) {
                    count++;
                }
                break;
            }
        }

        newCol = column + 1;
        for (; newCol < numColumns; newCol++) {
            if (seats[row][newCol] > 0) {
                if (seats[row][newCol] == 2) {
                    count++;
                }
                break;
            }
        }

        newRow = row - 1;
        newCol = column + 1;
        for (; newRow >= 0 && newCol < numColumns; newRow--, newCol++) {
            if (seats[newRow][newCol] > 0) {
                if (seats[newRow][newCol] == 2) {
                    count++;
                }
                break;
            }
        }
        return count;
    }
}
