package org.nextgen.hackerranksolution.poisonousplants;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nextgen.hackerranksolution.poisonousplants.Solution.entryPoint;
import static org.nextgen.hackerranksolution.poisonousplants.Solution.longestLifeSpanFast;

public class PoisonousPlantsTest {
    @Test
    public void zeroLifeSpanIfMorePoisonousThanImmediateLeft() throws Exception {
        int[] drugDoses = {1, 2, 3};
        assertThat(longestLifeSpanFast(drugDoses)).isEqualTo(1);
    }

    @Test
    public void livesForeverIfLessPoisonousThanEverythingToTheLeft() throws Exception {
        int[] drugDoses = {6, 5, 3};
        assertThat(longestLifeSpanFast(drugDoses)).isEqualTo(0);
    }

    @Test
    public void survivalTest1() throws Exception {
        int[] drugDoses = {1, 2, 2};
        assertThat(longestLifeSpanFast(drugDoses)).isEqualTo(2);
    }

    @Test
    public void survivalTest4() throws Exception {
        int[] drugDoses = {1, 1, 1};
        assertThat(longestLifeSpanFast(drugDoses)).isEqualTo(0);
    }



    @Test
    public void survivalTest3() throws Exception {
        int[] drugDoses = {1, 2, 3, 2, 4, 2, 8, 5, 3};
        assertThat(longestLifeSpanFast(drugDoses)).isEqualTo(3);
    }


    @Test
    public void theWholeTest() throws Exception {
        File f = new File("src/test/resources/poisonousplants.txt");
        entryPoint(new Scanner(f));
    }

}
