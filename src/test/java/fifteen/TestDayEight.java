package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayEight {

    @Test
    void emptyQuotes() {
        assertThat(Matchsticks.extraCharCount("\"\"")).isEqualTo(2);
    }

    @Test
    void escapeQuote() {
        assertThat(Matchsticks.extraCharCount("\"aaa\\\"aaa\"")).isEqualTo(3);
    }

    @Test
    void escapeHexa() {
        assertThat(Matchsticks.extraCharCount("\"\\x27\"")).isEqualTo(5);
    }

    @Test
    void testFile() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day8test.txt").getFile();
        long total = InputUtil.getAllLinesFromFile(input).mapToInt(Matchsticks::extraCharCount).sum();
        assertThat(total).isEqualTo(12);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day8input.txt").getFile();
        long total = InputUtil.getAllLinesFromFile(input).mapToInt(Matchsticks::extraCharCount).sum();
        assertThat(total).isEqualTo(1371);
    }

    @Test
    void emptyQuotesEncoded() {
        assertThat(Matchsticks.extraEncodedCharCount("\"\"")).isEqualTo(4);
    }

    @Test
    void escapeQuoteEncoded() {
        assertThat(Matchsticks.extraEncodedCharCount("\"aaa\\\"aaa\"")).isEqualTo(6);
    }

    @Test
    void escapeHexaEncoded() {
        assertThat(Matchsticks.extraEncodedCharCount("\"\\x27\"")).isEqualTo(5);
    }

    @Test
    void testFileEncoded() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day8test.txt").getFile();
        long total = InputUtil.getAllLinesFromFile(input).mapToInt(Matchsticks::extraEncodedCharCount).sum();
        assertThat(total).isEqualTo(19);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day8input.txt").getFile();
        long total = InputUtil.getAllLinesFromFile(input).mapToInt(Matchsticks::extraEncodedCharCount).sum();
        assertThat(total).isEqualTo(2117);
    }
}
