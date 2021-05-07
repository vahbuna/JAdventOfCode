package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class TestDayEleven {

    @Test
    void example() {
        assertThat(CorporatePolicy.newPassword("abcdefgh")).isEqualTo("abcdffaa");
    }

    @Test
    void exampleBis() {
        assertThat(CorporatePolicy.newPassword("ghijklmn")).isEqualTo("ghjaabcc");
    }

    @Test
    void partOne() {
        assertThat(CorporatePolicy.newPassword("hxbxwxba")).isEqualTo("hxbxxyzz");
    }

    @Test
    void partTwo() {
        assertThat(CorporatePolicy.newPassword("hxbxxzaa")).isEqualTo("hxcaabcc");
    }
}
