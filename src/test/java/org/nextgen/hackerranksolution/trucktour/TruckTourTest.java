package org.nextgen.hackerranksolution.trucktour;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class TruckTourTest {
    private Solution solution = new Solution();

    @Test
    public void findPathToCompleteCircle() throws Exception {
        Scanner inputData = new Scanner(new File("src/test/resources/trucktour.txt"));
        solution.entryPoint(inputData);
    }
}
