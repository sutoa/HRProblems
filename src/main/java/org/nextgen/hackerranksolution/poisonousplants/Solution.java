package org.nextgen.hackerranksolution.poisonousplants;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner inputData = new Scanner(System.in);
        entryPoint(inputData);
    }

    protected static void entryPoint(Scanner inputData) {
        final int[] plants = readInput(inputData);
        System.out.println(longestLifeSpanFast(plants));
    }

    protected static int longestLifeSpanFast(int[] plants) {
        Deque<Plant> survivedPlants = new ArrayDeque<>();
        int maxLife = 0;
        boolean plantDied = false;
        for (int i = 0; i < plants.length; i++) {
            final int currentDose = plants[i];
            if (survivedPlants.isEmpty()) {
                survivedPlants.push(new Plant(currentDose, 0));
                continue;
            }

            if(newSmallestDosage(survivedPlants, currentDose)){
                survivedPlants.clear();
                survivedPlants.push(new Plant(currentDose, maxLife));
                continue;
            }

            if(currentDose <= plants[i-1]) {
                findMaxLifeAfterKiller(survivedPlants, i, currentDose);
                maxLife = Math.max(survivedPlants.peek().lifeSpan, maxLife);
                continue;
            } else {
                plantDied = true;
                continue;
            }

        }

        return plantDied ? maxLife + 1 : 0;

    }

    private static boolean newSmallestDosage(Deque<Plant> survivedPlants, int currentDose) {
        return currentDose <= survivedPlants.peekLast().drugDose;
    }

    private static void findMaxLifeAfterKiller(Deque<Plant> survivedPlants, int idx, int currentDose) {
        int maxLifeSpan = 0;
        while(true){
            if(survivedPlants.peek().drugDose >= currentDose){
                maxLifeSpan = Math.max(maxLifeSpan, survivedPlants.pop().lifeSpan);
            } else {
                break;
            }
        }
        survivedPlants.push(new Plant(currentDose, maxLifeSpan + 1));
    }


    protected static int[] readInput(Scanner inputData) {
        final int numberOfPlants = inputData.nextInt();
        int[] plants = new int[numberOfPlants];
        for (int i = 0; i < numberOfPlants; i++) {
            plants[i] = inputData.nextInt();
        }
        return plants;
    }

}

class Plant {
    static final int FOREVER = -1;
    public static final int NOTHING = -1;

    int idx;
    int drugDose;
    int lifeSpan;
    int killIdx;

    public Plant(int drugAmount, int lifeSpan) {
        this.drugDose = drugAmount;
        this.lifeSpan = lifeSpan;
    }

    public boolean lessPoisonousThan(int dose){
        return drugDose < dose;
    }

    public boolean livesForever(){
        return lifeSpan == FOREVER;
    }

    public boolean livesLongerThan(int lifeSpan){
        return this.lifeSpan > lifeSpan;
    }

}

