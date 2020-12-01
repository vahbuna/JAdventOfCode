package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayOne {
    @Test
    void zero() {
        assertThat(NotQuiteLisp.getFloor("(())")).isZero();
        assertThat(NotQuiteLisp.getFloor("()()")).isZero();
    }

    @Test
    void three() {
        assertThat(NotQuiteLisp.getFloor("(((")).isEqualTo(3);
        assertThat(NotQuiteLisp.getFloor("(()(()(")).isEqualTo(3);
        assertThat(NotQuiteLisp.getFloor("))(((((")).isEqualTo(3);
    }

    @Test
    void firstBasement() {
        assertThat(NotQuiteLisp.getFloor("())")).isEqualTo(-1);
        assertThat(NotQuiteLisp.getFloor("))(")).isEqualTo(-1);
    }

    @Test
    void thirdBasement() {
        assertThat(NotQuiteLisp.getFloor(")))")).isEqualTo(-3);
        assertThat(NotQuiteLisp.getFloor(")())())")).isEqualTo(-3);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day1input.txt").getFile();
        assertThat(NotQuiteLisp.getFloor(InputUtil.getFirstLineFromFile(input))).isEqualTo(138);
    }

    @Test
    void basementAtOne() {
        assertThat(NotQuiteLisp.basementAt(")")).isEqualTo(1);
    }

    @Test
    void basementAtFive() {
        assertThat(NotQuiteLisp.basementAt("()())")).isEqualTo(5);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day1input.txt").getFile();
        int position = NotQuiteLisp.basementAt(InputUtil.getFirstLineFromFile(input));
        assertThat(position).isEqualTo(1771);
    }
}
