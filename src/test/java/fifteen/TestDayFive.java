package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFive {

    @Test
    void aaa() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNice("aaa")).isTrue();
        assertThat(DoesntHeHaveInternElvesForThis.isStringNicer("aaa")).isFalse();
    }

    @Test
    void ugknbfddgicrmopn() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNice("ugknbfddgicrmopn")).isTrue();
    }

    @Test
    void jchzalrnumimnmhp() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNice("jchzalrnumimnmhp")).isFalse();
    }

    @Test
    void haegwjzuvuyypxyu() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNice("haegwjzuvuyypxyu")).isFalse();
    }

    @Test
    void dvszwmarrgswjxmb() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNice("dvszwmarrgswjxmb")).isFalse();
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day5input.txt").getFile();
        assertThat(DoesntHeHaveInternElvesForThis.niceCount(InputUtil.getAllLinesFromFile(input))).isEqualTo(236);
    }

    @Test
    void qjhvhtzxzqqjkmpb() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNicer("qjhvhtzxzqqjkmpb")).isTrue();
    }

    @Test
    void xxyxx() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNicer("xxyxx")).isTrue();
    }

    @Test
    void uurcxstgmygtbstg() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNicer("uurcxstgmygtbstg")).isFalse();
    }

    @Test
    void ieodomkazucvgmuy() {
        assertThat(DoesntHeHaveInternElvesForThis.isStringNicer("ieodomkazucvgmuy")).isFalse();
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day5input.txt").getFile();
        assertThat(DoesntHeHaveInternElvesForThis.nicerCount(InputUtil.getAllLinesFromFile(input))).isEqualTo(51);
    }
}
