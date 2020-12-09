package twenty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BinaryBoarding {

    private final int maxSeatId;
    private final List<Integer> seatIds;

    public BinaryBoarding(Stream<String> boardingPasses) {
        seatIds = boardingPasses.mapToInt(pass -> {
            int row = binSearch(pass.substring(0, 7));
            int col = binSearch(pass.substring(7));
            return row * 8 + col;
        }).boxed().collect(Collectors.toList());
        maxSeatId = Collections.max(seatIds);
    }

    private int binSearch(String binarySpace) {
        int low = 0;
        int high = (int) Math.pow(2, binarySpace.length()) - 1;
        for (char dir: binarySpace.toCharArray()) {
            if (dir == 'L' || dir == 'F') {
                high = (high + low) / 2;
            } else {
                low = (low + high) / 2;
            }
        }
        return high;
    }

    public int getMaxSeatId() {
        return maxSeatId;
    }

    public int getMissingSeatId() {
        Collections.sort(seatIds);
        int missingSeat = -1;
        for (int i = 1; i < seatIds.size(); i++) {
            if (seatIds.get(i) - seatIds.get(i - 1) > 1) {
                missingSeat = seatIds.get(i) - 1;
                break;
            }
        }
        return missingSeat;
    }
}
