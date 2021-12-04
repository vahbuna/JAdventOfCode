package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class TestDayTwenty {

    @Test
    void example() {
        assertThat(InfiniteElvesAndInfiniteHouses.lowestHouseNumber(70, 10, Integer.MAX_VALUE))
                .isEqualTo(4);
    }

    @Test
    void partOne() {
        assertThat(InfiniteElvesAndInfiniteHouses.lowestHouseNumber(34000000, 10, Integer.MAX_VALUE))
                .isEqualTo(786240);
    }

    @Test
    void partTwo() {
        assertThat(InfiniteElvesAndInfiniteHouses.lowestHouseNumber(34000000, 11, 50))
                .isEqualTo(831600);
    }
}
