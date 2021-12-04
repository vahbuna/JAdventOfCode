package common;

import java.util.List;
import java.util.Map;

public abstract class Grid {

    private int[][] space;

    private final int numRows;

    private final int numColumns;

    private final Map<Character, Integer> statusMapping;

    private final char[] reverseMapping;

    protected Grid(List<String> input, Map<Character, Integer> mapping) {
        numRows = input.size();
        numColumns = input.get(0).length();
        space = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                    space[i][j] = mapping.get(input.get(i).charAt(j));
            }
        }
        statusMapping = mapping;
        reverseMapping = new char[mapping.size()];
        for (Map.Entry<Character, Integer> map: statusMapping.entrySet()) {
            reverseMapping[map.getValue()] = map.getKey();
        }
    }

    public int countNeighboursWithStatus(int row, int column, char cStatus) {
        int count = 0;
        int status = statusMapping.get(cStatus);

        if (row > 0 && space[row - 1][column] == status) {
            count++;
        }
        if (row > 0 && column > 0 && space[row - 1][column - 1] == status) {
            count++;
        }
        if (row < numRows - 1 && space[row + 1][column] == status) {
            count++;
        }
        if (row < numRows - 1 && column < numColumns - 1 && space[row + 1][column + 1] == status) {
            count++;
        }
        if (column > 0 && space[row][column - 1] == status) {
            count++;
        }
        if (column < numColumns - 1 && space[row][column + 1] == status) {
            count++;
        }
        if (row > 0 && column < numColumns - 1 && space[row - 1][column + 1] == status) {
            count++;
        }
        if (row < numRows - 1 && column > 0 && space[row + 1][column - 1] == status) {
            count++;
        }
        return count;
    }

    public int getNumRows() { return numRows; }

    public int getNumColumns() { return numColumns; }

    public char getStatus(int i, int j) {
        return reverseMapping[space[i][j]];
    }

    public int getStatusMapping(char status) {
        return statusMapping.get(status);
    }

    public void copyStatus(final int[][] newState) {
        for (int i = 0; i < numRows; i++) {
            space[i] = newState[i].clone();
        }
    }

    public int countStatus(char cStatus) {
        int status = statusMapping.get(cStatus);
        int count = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (space[i][j] == status) {
                    count++;
                }
            }
        }
        return count;
    }

    public void draw() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(reverseMapping[space[i][j]] + " ");
            }
        System.out.println();
        }
    }

    public int[][] getGrid() {
        return space.clone();
    }

    public abstract void update();

}
