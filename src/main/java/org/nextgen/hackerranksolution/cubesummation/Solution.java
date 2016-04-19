package org.nextgen.hackerranksolution.cubesummation;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private final static String UPDATE = "UPDATE";
    private Map<Coordinate, Block> weightedBlocks = new HashMap<>();

    public static void main(String[] args) {
        entryPoint(new Scanner(System.in));
    }

    static void entryPoint(Scanner inputData) {
        Solution s = new Solution();
        final int numberOfTests = inputData.nextInt();
        for (int i = 0; i < numberOfTests; i++) {
            s.processCube(inputData);
        }
    }

    private void processCube(Scanner inputData) {
        weightedBlocks.clear();
        int cubeSize = inputData.nextInt();
        int numberOfOperations = inputData.nextInt();
        for (int i = 0; i < numberOfOperations; i++) {
            String op = inputData.next();
            if (op.equals(UPDATE)) {
                processUpdate(inputData);
            } else {
                System.out.println(getCubeWeight(inputData));
            }
        }
    }

    private long getCubeWeight(Scanner inputData) {
        final Coordinate start = new Coordinate(inputData.nextInt(), inputData.nextInt(), inputData.nextInt());
        final Coordinate end = new Coordinate(inputData.nextInt(), inputData.nextInt(), inputData.nextInt());

        final Set<Block> weightedBlocksInCube = findBlocksInCube(start, end, new HashSet(weightedBlocks.values()));
        return weightedBlocksInCube.stream()
                .mapToLong(b -> b.getValue())
                .sum();

    }

    private void processUpdate(Scanner inputData) {
        int x = inputData.nextInt();
        int y = inputData.nextInt();
        int z = inputData.nextInt();
        int value = inputData.nextInt();
        final Coordinate coordinate = new Coordinate(x, y, z);
        weightedBlocks.put(coordinate, new Block(coordinate, value));
    }

    public Set<Block> findBlocksInCube(Coordinate startCoordinate, Coordinate endCoordinate, Set<Block> blocksWithValue) {
        return blocksWithValue.stream()
                .filter(b -> blockInCube(b, startCoordinate, endCoordinate))
                .collect(Collectors.toSet());
    }

    private boolean blockInCube(Block b, Coordinate startCoordinate, Coordinate endCoordinate) {
        return b.getCoordinate().between(startCoordinate, endCoordinate);
    }
}

class Coordinate {
    int x;
    int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    int z;


    Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean between(Coordinate startCoordinate, Coordinate endCoordinate) {
        return startCoordinate.x <= x && x <= endCoordinate.x &&
                startCoordinate.y <= y && y <= endCoordinate.y &&
                startCoordinate.z <= z && z <= endCoordinate.z;
    }
}

class Block {
    Coordinate coordinate;
    int value;

    Block(Coordinate coordinate, int value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    void setValue(int value) {
        this.value = value;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    int getValue() {
        return value;
    }
}
