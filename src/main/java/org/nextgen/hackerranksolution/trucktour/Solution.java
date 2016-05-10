package org.nextgen.hackerranksolution.trucktour;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * while trying stations, if failed with 1..n stations, then there's no need to try 2-n as the starting point,
 * because we will get negative value at some point.
 */
public class Solution {
    private List<Pump> pumps = new ArrayList<>();
    private boolean completed = false;
    private int starting = 0;

    public static void main(String[] arges) throws FileNotFoundException {
        Scanner inputData = new Scanner(System.in);
        Solution s = new Solution();
        s.entryPoint(inputData);
    }

    protected void entryPoint(Scanner inputData) {
        int count = inputData.nextInt();
        for (int i = 0; i < count; i++) {
            pumps.add(new Pump(i, inputData.nextInt(), inputData.nextInt()));
        }

        while(incompleteCircle()) {
            startAt(starting, pumps);
        }

    }

    private void startAt(int starting, List<Pump> pumps) {

        Collections.rotate(pumps, -starting);

        long totalCapacity = 0;
        long totalDistance = 0;
        int j;
        for (j = 0; j < pumps.size(); j++) {
            Pump p = pumps.get(j);
            totalCapacity += p.capacity;
            totalDistance += p.distanceToNextPump;
            long x = totalCapacity - totalDistance;
            if (x < 0) {
                this.starting = j+1;
                break;
            }
        }
        if(j == pumps.size()) {
            completed = true;
            System.out.println(pumps.get(0).id);
        }

    }

    private boolean incompleteCircle() {
        return !completed;
    }
}

class Pump {
    int  id;
    long capacity;
    long distanceToNextPump;

    public Pump(int id, long capacity, long distanceToNextPump) {
        this.id = id;
        this.capacity = capacity;
        this.distanceToNextPump = distanceToNextPump;
    }
}

