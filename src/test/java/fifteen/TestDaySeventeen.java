package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestDaySeventeen {

    @Test
    void example() {
        NoSuchThingAsTooMuch refrigerator = new NoSuchThingAsTooMuch(Arrays.asList(20, 15, 10, 5, 5), 25);
        assertThat(refrigerator.combinationOfContainers()).isEqualTo(4);
    }

    @Test
    void partOne() {
        NoSuchThingAsTooMuch refrigerator = new NoSuchThingAsTooMuch(Arrays.asList(11, 30, 47, 31, 32, 36, 3,
                1, 5, 3, 32, 36, 15, 11, 46, 26, 28, 1, 19, 3), 150);
        assertThat(refrigerator.combinationOfContainers()).isEqualTo(4372);
    }

    @Test
    void exampleB() {
        NoSuchThingAsTooMuch refrigerator = new NoSuchThingAsTooMuch(Arrays.asList(20, 15, 10, 5, 5), 25);
        assertThat(refrigerator.combinationOfMinimumContainers()).isEqualTo(3);
    }

    @Test
    void partTwo() {
        NoSuchThingAsTooMuch refrigerator = new NoSuchThingAsTooMuch(Arrays.asList(11, 30, 47, 31, 32, 36, 3,
                1, 5, 3, 32, 36, 15, 11, 46, 26, 28, 1, 19, 3), 150);
        assertThat(refrigerator.combinationOfMinimumContainers()).isEqualTo(4);
    }
}
