package fifteen;


public final class NotQuiteLisp {

    private NotQuiteLisp() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    /** Problem description: https://adventofcode.com/2015/day/1 */
    public static int getFloor(final String path) {
        int destination = 0;
        if (path == null || path.length() == 0) {
            return destination;
        }

        for (char step: path.toCharArray()) {
            if (')' == step) {
                destination--;
            } else if ('(' == step) {
                destination++;
            }
        }
        return destination;
    }

    /** Problem: https://adventofcode.com/2015/day/1#part2 */
    public static int basementAt(final String path) {
        int position = 0;
        if (path == null || path.length() == 0) {
            return position;
        }
        int destination = 0;
        for (position = 0; position < path.length(); position++) {
            if (path.charAt(position) == ')') {
                destination--;
            } else if (path.charAt(position) == '(') {
                destination++;
            }
            if (destination < 0) {
                break;
            }
         }
        return position + 1;
    }
}
