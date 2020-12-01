package fifteen;

import java.util.HashMap;
import java.util.Map;

public final class PerfectlySphericalHousesinaVacuum {
    static final class Location {
        final int x;
        final int y;

        public Location(final int pos1, final int pos2) {
            x = pos1;
            y = pos2;
        }

        public Location toNorth() {
            return new Location(x, y + 1);
        }

        public Location toSouth() {
            return new Location(x, y - 1);
        }

        public Location toEast() {
            return new Location(x + 1, y);
        }

        public Location toWest() {
            return new Location(x - 1, y);
        }

        @Override
        public String toString() {
            return String.format("Currently at (%d, %d)", x, y);
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Location other = (Location) obj;
            return (x == other.x && y == other.y);
        }
    }

    private final Map<Location, Integer> visits = new HashMap<>();

    public PerfectlySphericalHousesinaVacuum() {
        Location start = new Location(0, 0);
        visits.put(start, 1);
    }

    public void deliverPresents(final String route) {
        Location current = new Location(0, 0);
        for (char direction: route.toCharArray()) {
            if (direction == '^') {
                current = current.toNorth();
            } else if (direction == 'v') {
                current = current.toSouth();
            } else if (direction == '>') {
                current = current.toEast();
            } else if (direction == '<') {
                current = current.toWest();
            }
            int count = visits.getOrDefault(current, 0);
            visits.put(current, count + 1);
        }
    }

    /** https://adventofcode.com/2015/day/3 */
    public long housesAtLeastOne() {
        return visits.size();
    }

    public Map<Location, Integer> getVisitCount() {
        return visits;
    }
}
