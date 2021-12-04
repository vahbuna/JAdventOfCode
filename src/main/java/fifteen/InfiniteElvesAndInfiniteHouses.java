package fifteen;

/**
 * Elf is an AP: i + (n-1) * i If a house receives multiple presents => that house is present in multiple APs
 * https://adventofcode.com/2015/day/20
 */
public class InfiniteElvesAndInfiniteHouses {

    private InfiniteElvesAndInfiniteHouses() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }
    /* brute force takes 30 minutes
    public long lowestHouseNumberSlow() {
        for (int houseNumber = 1; houseNumber < presents; houseNumber++) {
            long totalPresents = 0;
            for (Integer factor: factors(houseNumber)) {
                totalPresents += 10 * factor;
            }
            if (totalPresents >= presents) {
                return houseNumber;
            }
        }
        return -1;
    }
     */

    public static int lowestHouseNumber(final int presents, final int numPresentsPerElf, int maxHouses) {
        int[] house = new int[presents + 1];
        for (int elf = 1; elf < presents / numPresentsPerElf; elf++) {
            int limit = Math.min(maxHouses, presents / elf);
            for (int n = 1; n <= limit; n++) {
                house[n * elf] += numPresentsPerElf * elf;
            }
        }
        for (int i = 0; i < house.length; i++) {
            if (house[i] >= presents) {
                return i;
            }
        }
        return -1;
    }
}
