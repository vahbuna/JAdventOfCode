package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDaySeven {

    @Test
    void assign() {
        SomeAssemblyRequired kit = new SomeAssemblyRequired();
        kit.execute("123 -> x");
        assertThat(kit.getValueOf("x")).isEqualTo(123);
    }

    @Test
    void and() {
        SomeAssemblyRequired kit = new SomeAssemblyRequired();
        kit.execute("123 -> x");
        kit.execute("456 -> y");
        kit.execute("x AND y -> d");
        assertThat(kit.getValueOf("d")).isEqualTo(72);
    }

    @Test
    void example() {
        SomeAssemblyRequired kit = new SomeAssemblyRequired();
        kit.execute("123 -> x");
        kit.execute("456 -> y");
        kit.execute("x AND y -> d");
        kit.execute("x OR y -> e");
        kit.execute("x LSHIFT 2 -> f");
        kit.execute("y RSHIFT 2 -> g");
        kit.execute("NOT x -> h");
        kit.execute("NOT y -> i");
        assertThat(kit.getValueOf("e")).isEqualTo(507);
        assertThat(kit.getValueOf("f")).isEqualTo(492);
        assertThat(kit.getValueOf("g")).isEqualTo(114);
        assertThat(kit.getValueOf("h")).isEqualTo(65412);
        assertThat(kit.getValueOf("i")).isEqualTo(65079);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day7input.txt").getFile();
        SomeAssemblyRequired kit = new SomeAssemblyRequired();
        InputUtil.getAllLinesFromFile(input).forEach(kit::execute);
        assertThat(kit.getValueOf("a")).isEqualTo(3176);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day7input.txt").getFile();
        SomeAssemblyRequired kit = new SomeAssemblyRequired();
        kit.execute("3176 -> b");
        InputUtil.getAllLinesFromFile(input)
                .filter(i -> !"44430 -> b".equals(i))
                .forEach(kit::execute);
        assertThat(kit.getValueOf("a")).isEqualTo(14710);
    }
}
