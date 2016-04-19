package org.nextgen.hackerranksolution.cubesummation;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CubeSummationTest {
    private Solution solution = new Solution();

    @Test
    public void findBlocksWithACube() throws Exception {
        Block b1 = new Block(new Coordinate(1, 1, 1), 10);
        Block b2 = new Block(new Coordinate(10, 10, 10), 20);

        Set<Block> blocksWithValue = new HashSet<>(Arrays.asList(b1, b1));

        Coordinate startCoordinate = new Coordinate(0, 0, 0);
        Coordinate endCoordinate = new Coordinate(5, 5, 5);
        assertThat(solution.findBlocksInCube(startCoordinate, endCoordinate, blocksWithValue)).containsExactly(b1);

    }

    @Test
    public void theWholeTest() throws Exception {
        File f = new File("src/test/resources/cubesum.txt");
        Solution.entryPoint(new Scanner(f));

    }
}
