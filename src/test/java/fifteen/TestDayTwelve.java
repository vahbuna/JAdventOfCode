package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayTwelve {

    @Test
    void six() {
        assertThat(JSAbacusFrameworkIO.sumAll("[1,2,3]"))
                .isEqualTo(JSAbacusFrameworkIO.sumAll("{\"a\":2,\"b\":4}"));
    }

    @Test
    void three() {
        assertThat(JSAbacusFrameworkIO.sumAll("[[[3]]]"))
                .isEqualTo(JSAbacusFrameworkIO.sumAll("{\"a\":{\"b\":4},\"c\":-1}"));
    }

    @Test
    void zero() {
        assertThat(JSAbacusFrameworkIO.sumAll("{\"a\":[-1,1]}"))
                .isEqualTo(JSAbacusFrameworkIO.sumAll("[-1,{\"a\":1}]"));
        assertThat(JSAbacusFrameworkIO.sumAll("[]"))
                .isEqualTo(JSAbacusFrameworkIO.sumAll("{}"));
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day12input.txt").getFile();
        Assertions.assertThat(JSAbacusFrameworkIO.sumAll(InputUtil.getFirstLineFromFile(input))).isEqualTo(119433);
    }

    @Test
    void redSix() {
        assertThat(JSAbacusFrameworkIO.sumExceptRed("[1,2,3]"))
                .isEqualTo(JSAbacusFrameworkIO.sumExceptRed("[1,\"red\",5]"));
    }

    @Test
    void redFour() {
        assertThat(JSAbacusFrameworkIO.sumExceptRed("[1,{\"c\":\"red\",\"b\":2},3]")).isEqualTo(4);
    }

    @Test
    void redZero() {
        assertThat(JSAbacusFrameworkIO.sumExceptRed("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")).isZero();
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day12input.txt").getFile();
        Assertions.assertThat(JSAbacusFrameworkIO.sumExceptRed(InputUtil.getFirstLineFromFile(input))).isEqualTo(68466);
    }
}
