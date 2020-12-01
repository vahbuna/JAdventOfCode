package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TestDayFour {

    @Test
    void abcdef() {
        assertThat(TheIdealStockingStuffer.bruteForce("abcdef", 5)).isEqualTo(609043);
    }

    @Test
    void pqrstuv() {
        assertThat(TheIdealStockingStuffer.bruteForce("pqrstuv", 5)).isEqualTo(1048970);
    }

    @Test
    void partOne() {
        assertThat(TheIdealStockingStuffer.bruteForce("bgvyzdsv", 5)).isEqualTo(254575);
    }

    @Test
    void partTwo() {
        assertThat(TheIdealStockingStuffer.bruteForce("bgvyzdsv", 6)).isEqualTo(1038736);
    }
}
