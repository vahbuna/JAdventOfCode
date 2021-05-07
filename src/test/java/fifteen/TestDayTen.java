package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class TestDayTen {

    @Test
    void one() {
        assertThat(ElvesLookElvesSay.lookSay("1")).isEqualTo("11");
    }

    @Test
    void eleven() {
        assertThat(ElvesLookElvesSay.lookSay("11")).isEqualTo("21");
    }

    @Test
    void twentyOne() {
        assertThat(ElvesLookElvesSay.lookSay("21")).isEqualTo("1211");
    }

    @Test
    void twelveEleven() {
        assertThat(ElvesLookElvesSay.lookSay("1211")).isEqualTo("111221");
    }

    @Test
    void threeTwelveTwosOnes() {
        assertThat(ElvesLookElvesSay.lookSay("111221")).isEqualTo("312211");
    }

    @Test
    void example() {
        assertThat(ElvesLookElvesSay.process("1", 5)).isEqualTo("312211");
    }

    @Test
    void partOne() {
        assertThat(ElvesLookElvesSay.process("3113322113", 40)).hasSize(329356);
    }

    @Test
    void partTwo() {
        assertThat(ElvesLookElvesSay.process("3113322113", 50)).hasSize(4666278);
    }
}
