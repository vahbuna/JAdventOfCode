package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayThree {

    @Test
    void four() {
        PerfectlySphericalHousesinaVacuum route = new PerfectlySphericalHousesinaVacuum();
        route.deliverPresents("^>v<");
        assertThat(route.housesAtLeastOne()).isEqualTo(4);
    }

    @Test
    void two() {
        PerfectlySphericalHousesinaVacuum route = new PerfectlySphericalHousesinaVacuum();
        route.deliverPresents("^v^v^v^v^v");
        assertThat(route.housesAtLeastOne()).isEqualTo(2);
        route = new PerfectlySphericalHousesinaVacuum();
        route.deliverPresents(">");
        assertThat(route.housesAtLeastOne()).isEqualTo(2);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day3input.txt").getFile();
        PerfectlySphericalHousesinaVacuum route = new PerfectlySphericalHousesinaVacuum();
        route.deliverPresents(InputUtil.getFirstLineFromFile(input));
        assertThat(route.housesAtLeastOne()).isEqualTo(2081);
    }

    @Test
    void roboThree() {
        PerfectlySphericalHousesinaVacuum santa = new PerfectlySphericalHousesinaVacuum();
        santa.deliverPresents("^");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> santaVisits = santa.getVisitCount();
        PerfectlySphericalHousesinaVacuum roboSanta = new PerfectlySphericalHousesinaVacuum();
        roboSanta.deliverPresents("v");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> roboSantaVisits = roboSanta.getVisitCount();
        santaVisits.forEach( (loc, count) -> roboSantaVisits.merge(loc, count, Integer::sum));
        assertThat(roboSantaVisits).hasSize(3);
    }

    @Test
    void roboThreeBis() {
        PerfectlySphericalHousesinaVacuum santa = new PerfectlySphericalHousesinaVacuum();
        santa.deliverPresents("^v");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> santaVisits = santa.getVisitCount();
        PerfectlySphericalHousesinaVacuum roboSanta = new PerfectlySphericalHousesinaVacuum();
        roboSanta.deliverPresents("><");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> roboSantaVisits = roboSanta.getVisitCount();
        santaVisits.forEach( (loc, count) -> roboSantaVisits.merge(loc, count, Integer::sum));
        assertThat(roboSantaVisits).hasSize(3);
    }

    @Test
    void roboEleven() {
        PerfectlySphericalHousesinaVacuum santa = new PerfectlySphericalHousesinaVacuum();
        santa.deliverPresents("^^^^^");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> santaVisits = santa.getVisitCount();
        PerfectlySphericalHousesinaVacuum roboSanta = new PerfectlySphericalHousesinaVacuum();
        roboSanta.deliverPresents("vvvvv");
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> roboSantaVisits = roboSanta.getVisitCount();
        santaVisits.forEach( (loc, count) -> roboSantaVisits.merge(loc, count, Integer::sum));
        assertThat(roboSantaVisits).hasSize(11);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day3input.txt").getFile();
        String path = InputUtil.getFirstLineFromFile(input);
        StringBuilder santaPathBuilder = new StringBuilder();
        StringBuilder roboSantaPathBuilder = new StringBuilder();
        for (int i = 0; i < path.length(); i += 2) {
            santaPathBuilder.append(path.charAt(i));
            roboSantaPathBuilder.append(path.charAt(i + 1));
        }
        PerfectlySphericalHousesinaVacuum santa = new PerfectlySphericalHousesinaVacuum();
        santa.deliverPresents(santaPathBuilder.toString());
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> santaVisits = santa.getVisitCount();
        PerfectlySphericalHousesinaVacuum roboSanta = new PerfectlySphericalHousesinaVacuum();
        roboSanta.deliverPresents(roboSantaPathBuilder.toString());
        Map<PerfectlySphericalHousesinaVacuum.Location, Integer> roboSantaVisits = roboSanta.getVisitCount();
        santaVisits.forEach( (loc, count) -> roboSantaVisits.merge(loc, count, Integer::sum));
        assertThat(roboSantaVisits).hasSize(2341);
    }
}
