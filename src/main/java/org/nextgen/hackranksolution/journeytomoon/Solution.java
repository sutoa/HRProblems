package org.nextgen.hackranksolution.journeytomoon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

public class Solution {
    private Set<Integer> groupedWithCountry = new HashSet<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        final InputContext inputData = solution.readInput();

        final Map<Integer, Set<Integer>> relationships = solution.setupCountryMenRelationship(inputData.relationships);
        final Set<Set<Integer>> peopleByCountry = solution.groupPeopleByCountry(inputData.numberOfPeople, relationships);
        final int i = solution.pairsFromDifferentCountries(peopleByCountry);
        System.out.println(i);

    }

    public InputContext readInput() {
        final Scanner input = new Scanner(System.in);

        int numberOfPeople = input.nextInt();
        final int numberOfPairs = input.nextInt();

        validateData(numberOfPeople, numberOfPairs);

        int[][] relationships = new int[numberOfPairs][2];
        for (int i = 0; i < numberOfPairs; i++) {
            relationships[i][0] = input.nextInt();
            relationships[i][1] = input.nextInt();
        }
        return new InputContext(numberOfPeople, relationships);
    }

    private void validateData(int numberOfPeople, int numberOfPair) {
        if (numberOfPeople > 100000 || numberOfPeople < 1)
            throw new IllegalArgumentException("Invalid N: " + numberOfPeople);
        if (numberOfPair > 10000 || numberOfPair < 1)
            throw new IllegalArgumentException("Invalid I: " + numberOfPeople);

    }

    public Map<Integer, Set<Integer>> setupCountryMenRelationship(int[][] countryMenPairs) {
        Map<Integer, Set<Integer>> countrymenRelationship = new HashMap<>();
        for (int[] pair : countryMenPairs) {
            sort(pair);
            addPairToRelationship(countrymenRelationship, pair);
            addPairToRelationship(countrymenRelationship, reverse(pair));
        }

        return countrymenRelationship;
    }

    private int[] reverse(int[] pair) {
        return new int[]{pair[1], pair[0]};
    }

    private void addPairToRelationship(Map<Integer, Set<Integer>> countrymenRelationship, int[] countryMenPairs) {
        Set<Integer> countrymen = countrymenRelationship.get(countryMenPairs[0]);
        if (countrymen == null) {
            countrymen = new HashSet<>();
            countrymenRelationship.put(countryMenPairs[0], countrymen);
        }
        countrymen.add(countryMenPairs[1]);

    }

    public Set<Set<Integer>> groupPeopleByCountry(int numberOfCountryMen, Map<Integer, Set<Integer>> countryMenPairs) {
        Set<Set<Integer>> countryMenGroups = new HashSet<>();
        countryMenPairs.keySet().stream().forEach(anchorMan -> {
            final Set<Integer> compatriots = findCompatriot(-1, anchorMan, countryMenPairs);
            if (manWithCompatriots(compatriots)) {
                countryMenGroups.add(compatriots);
            }
        });

        fillInIsolatedCountryMen(numberOfCountryMen, countryMenGroups);

        return countryMenGroups;
    }

    private void fillInIsolatedCountryMen(int numberOfCountryMen, Set<Set<Integer>> countryMenGroups) {
        IntStream.range(0, numberOfCountryMen)
                .filter(idx -> !alreadyGroupedWithACountry(idx))
                .forEach(idx -> {
                    countryMenGroups.add(new HashSet<>(asList(idx)));
                });

    }

    private boolean manWithCompatriots(Set<Integer> compatriat) {
        return !compatriat.isEmpty();
    }

    protected Set<Integer> findCompatriot(Integer parent, Integer anchorMan, Map<Integer, Set<Integer>> countryMenPairs) {
        if (alreadyGroupedWithACountry(anchorMan))
            return new HashSet<>();

        groupedWithCountry.add(anchorMan);

        Set<Integer> countryMen = new HashSet<>();
        countryMen.add(anchorMan);

        final Set<Integer> neighbors = countryMenPairs.get(anchorMan);
        if (neighbors == null) {
            return countryMen;
        }

        for (Integer neighbor : neighbors) {
            if (neighbor == parent) continue;
            final Set<Integer> compatriots = findCompatriot(anchorMan, neighbor, countryMenPairs);
            countryMen.addAll(compatriots);
        }
        return countryMen;
    }

    private boolean alreadyGroupedWithACountry(Integer anchorMan) {
        return groupedWithCountry.contains(anchorMan);
    }

    public int pairsFromDifferentCountries(List<Integer> peopleInCountries) {
        return getNumberOfCombination(2, peopleInCountries);
    }

    public int pairsFromDifferentCountries(Set<Set<Integer>> peopleInCountries) {
        return getNumberOfCombination(2, peopleInCountries);
    }

    public int getNumberOfCombination(int numberOfPeopleInCombo, Set<Set<Integer>> peopleInGroups) {
        final List<Integer> countrymenGroupSizes = peopleInGroups.stream().map(group -> group.size()).collect(Collectors.toList());
        return getNumberOfCombination(numberOfPeopleInCombo, countrymenGroupSizes);

    }

    public int getNumberOfCombination(int numberOfPeopleInCombo, List<Integer> peopleInGroups) {
        if (numberOfPeopleInCombo > peopleInGroups.size() || peopleInGroups.isEmpty())
            return 0;

        if (numberOfPeopleInCombo == 1) {
            return peopleInGroups.stream().mapToInt(Integer::intValue).sum();
        }

        final int nextNumberOfPeopleInCombo = Math.max(numberOfPeopleInCombo - 1, 0);
        return peopleInGroups.get(0) * getNumberOfCombination(nextNumberOfPeopleInCombo, peopleInGroups.subList(1, peopleInGroups.size()))
                + getNumberOfCombination(numberOfPeopleInCombo, peopleInGroups.subList(1, peopleInGroups.size()));

    }


    private class InputContext {
        int[][] relationships;
        int numberOfPeople;

        public InputContext(int numberOfPeople, int[][] relationships) {
            this.numberOfPeople = numberOfPeople;
            this.relationships = relationships;
        }
    }
}

