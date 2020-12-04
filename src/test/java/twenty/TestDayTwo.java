package twenty;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayTwo {

    @Test
    void abcde() {
        assertThat(PasswordPhilosophy.isPasswordValid("1-3 a: abcde")).isTrue();
    }


    @Test
    void ccccccccc() {
        assertThat(PasswordPhilosophy.isPasswordValid("2-9 c: ccccccccc")).isTrue();
    }

    @Test
    void cdefg() {
        assertThat(PasswordPhilosophy.isPasswordValid("1-3 b: cdefg")).isFalse();
    }

    @Test
    void count() {
        List<String> policies = Arrays.asList("1-3 a: abcde", "2-9 c: ccccccccc", "1-3 b: cdefg");
        long count = policies.stream().map(PasswordPhilosophy::isPasswordValid).filter(Boolean::booleanValue).count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day2input.txt").getFile();
        long count = InputUtil.getAllLinesFromFile(input)
                .map(PasswordPhilosophy::isPasswordValid)
                .filter(Boolean::booleanValue)
                .count();
        assertThat(count).isEqualTo(456);
    }

    @Test
    void abcdeNew() {
        assertThat(PasswordPhilosophy.isPasswordValidNew("1-3 a: abcde")).isTrue();
    }


    @Test
    void cccccccccNew() {
        assertThat(PasswordPhilosophy.isPasswordValidNew("2-9 c: ccccccccc")).isFalse();
    }

    @Test
    void cdefgNew() {
        assertThat(PasswordPhilosophy.isPasswordValidNew("1-3 b: cdefg")).isFalse();
    }

    @Test
    void countNew() {
        List<String> policies = Arrays.asList("1-3 a: abcde", "2-9 c: ccccccccc", "1-3 b: cdefg");
        long count = policies.stream().map(PasswordPhilosophy::isPasswordValidNew).filter(Boolean::booleanValue).count();
        assertThat(count).isOne();
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day2input.txt").getFile();
        long count = InputUtil.getAllLinesFromFile(input)
                .map(PasswordPhilosophy::isPasswordValidNew)
                .filter(Boolean::booleanValue)
                .count();
        assertThat(count).isEqualTo(308);
    }
}
