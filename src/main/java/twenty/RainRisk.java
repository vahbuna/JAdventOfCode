package twenty;

import java.util.List;

public final class RainRisk {

    private static class Ship {

        int x;

        int y;

        public Ship(int startX, int startY) {
            x = startX;
            y = startY;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private RainRisk() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    /**
     * https://adventofcode.com/2020/day/12
     */
    public static int manhattan(List<String> directions) {
        Ship ship = new Ship(0, 0);
        Ship wayPoint = new Ship(1, 0);
        for (String instruction : directions) {
            String command = instruction.substring(0, 1);
            int value = Integer.parseInt(instruction.substring(1));
            move(command, value, ship, wayPoint);
            if (command.equals("F")) {
                forward(value, ship, wayPoint);
            }
        }
        return Math.abs(ship.x) + Math.abs(ship.y);
    }

    /**
     * https://adventofcode.com/2020/day/12#part2
     */
    public static int manhattanBis(List<String> directions) {
        Ship ship = new Ship(0, 0);
        Ship wayPoint = new Ship(10, 1);
        for (String instruction : directions) {
            String command = instruction.substring(0, 1);
            int value = Integer.parseInt(instruction.substring(1));
            move(command, value, wayPoint, wayPoint);
            if (command.equals("F")) {
                forward(value, ship, wayPoint);
            }
        }
        return Math.abs(ship.x) + Math.abs(ship.y);
    }

    private static void move(String instruction, int value, Ship ship, Ship wayPoint) {
        switch (instruction) {
            case "N":
                ship.y += value;
                break;
            case "S":
                ship.y -= value;
                break;
            case "E":
                ship.x += value;
                break;
            case "W":
                ship.x -= value;
                break;
            case "R":
                rotate(wayPoint, Math.toRadians(-value));
                break;
            case "L":
                rotate(wayPoint, Math.toRadians(value));
                break;
        }
    }

    /**
     * reason for delay: didn't use Math.round
     */
    private static void rotate(Ship wayPoint, double radians) {
        int rxDir = (int) Math.round(wayPoint.x * Math.cos(radians) - wayPoint.y * Math.sin(radians));
        int ryDir = (int) Math.round(wayPoint.x * Math.sin(radians) + wayPoint.y * Math.cos(radians));
        wayPoint.x = rxDir;
        wayPoint.y = ryDir;
    }

    private static void forward(int value, Ship ship, Ship wayPoint) {
        ship.x += wayPoint.x * value;
        ship.y += wayPoint.y * value;
    }
}
