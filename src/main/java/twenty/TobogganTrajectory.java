package twenty;

import java.util.List;

public final class TobogganTrajectory {

    private TobogganTrajectory() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static int getTreeCount(List<String> localGeology, int stepRight, int stepDown) {
        int treeCnt = 0;
        int positionX = 0;
        for (int positionY = 0; positionY < localGeology.size(); positionY += stepDown) {
            String row = localGeology.get(positionY);
            if (row.charAt(positionX) == '#') {
                treeCnt++;
            }
            positionX = (positionX + stepRight) % row.length();
        }
        return treeCnt;
    }
}
