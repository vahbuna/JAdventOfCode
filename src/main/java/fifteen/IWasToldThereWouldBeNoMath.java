package fifteen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://adventofcode.com/2015/day/2
 */
public final class IWasToldThereWouldBeNoMath {

    private final List<Dimension> dimensionList;

    final class Dimension {
        private final int [] dimensions = new int[3];
        private final int slack;

        Dimension(final int l, final int w, final int h) {
            dimensions[0] = l;
            dimensions[1] = w;
            dimensions[2] = h;
            Arrays.sort(dimensions);
            slack = dimensions[0] * dimensions[1];
        }

        public int getAreaWithSlack() {
            return 2 * (slack + dimensions[1] * dimensions[2] + dimensions[0] * dimensions[2]) + slack;
        }

        public int getRibbon() {
            return slack * dimensions[2] + 2 * (dimensions[0] + dimensions[1]);
        }
    }
    IWasToldThereWouldBeNoMath(Stream<String> dimensions) {
        dimensionList = dimensions.map(
                dim -> {
                    String[] dimension = dim.split("x");
                    return new Dimension(Integer.parseInt(dimension[0]),
                            Integer.parseInt(dimension[1]),
                            Integer.parseInt(dimension[2]));
                }
        ).collect(Collectors.toList());
    }

    public long wrappingPaper() {
        return dimensionList.stream().mapToLong(Dimension::getAreaWithSlack).sum();
    }

    public long ribbonAndBow() {
        return dimensionList.stream().mapToLong(Dimension::getRibbon).sum();
    }
}
