package org.nextgen.hackerranksolution.journeytomoom;

import org.junit.Test;
import org.nextgen.hackranksolution.journeytomoon.Solution;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JourneyToTheMoonTest {
    private Solution solution = new Solution();

    @Test
    public void buildCountryMenRelationshipFromInput() throws Exception {
        int[][] countryMenPairsInput = {
                {1, 0},
                {1, 2},
                {3, 4},
                {5, 2},
                {3, 6}
        };

        final Map<Integer, Set<Integer>> countrymenRelationship = solution.setupCountryMenRelationship(countryMenPairsInput);

        assertThat(countrymenRelationship.get(1)).containsExactly(0, 2);
        assertThat(countrymenRelationship.get(3)).contains(6, 4).hasSize(2);
        assertThat(countrymenRelationship.get(2)).contains(5, 1).hasSize(2);
        assertThat(countrymenRelationship.get(0)).containsExactly(1);
        assertThat(countrymenRelationship.get(5)).containsExactly(2);
        assertThat(countrymenRelationship.get(6)).containsExactly(3);
    }

    @Test
    public void groupPeopleByCountry() throws Exception {
        Map<Integer, Set<Integer>> countryMenRelationship = new HashMap<>();
        countryMenRelationship.put(0, new HashSet<>(Arrays.asList(1)));
        countryMenRelationship.put(1, new HashSet<>(Arrays.asList(2)));
        countryMenRelationship.put(3, new HashSet<>(Arrays.asList(4)));
        countryMenRelationship.put(2, new HashSet<>(Arrays.asList(6)));

        int numberOfCountryMen = 7;

        final Set<Set<Integer>> countryMenGroups = solution.groupPeopleByCountry(numberOfCountryMen, countryMenRelationship);

        assertThat(countryMenGroups).hasSize(3);
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(1, 0, 2, 6)));
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(3, 4)));
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(5)));

    }

    @Test
    public void groupPeopleByCountryWithConnectedGroup() throws Exception {
        Map<Integer, Set<Integer>> countryMenRelationship = new HashMap<>();
        countryMenRelationship.put(0, new HashSet<>(Arrays.asList(2)));
        countryMenRelationship.put(2, new HashSet<>(Arrays.asList(8, 0)));
        countryMenRelationship.put(1, new HashSet<>(Arrays.asList(4, 8)));
        countryMenRelationship.put(3, new HashSet<>(Arrays.asList(5, 6)));
        countryMenRelationship.put(8, new HashSet<>(Arrays.asList(2, 1)));
        countryMenRelationship.put(4, new HashSet<>(Arrays.asList(1)));
        countryMenRelationship.put(5, new HashSet<>(Arrays.asList(3)));
        countryMenRelationship.put(6, new HashSet<>(Arrays.asList(3)));


        int numberOfCountryMen = 9;

        final Set<Set<Integer>> countryMenGroups = solution.groupPeopleByCountry(numberOfCountryMen, countryMenRelationship);

        assertThat(countryMenGroups).hasSize(3);
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(1, 0, 2, 4, 8)));
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(3, 5, 6)));
        assertThat(countryMenGroups).contains(new HashSet<Integer>(Arrays.asList(7)));

    }

    @Test
    public void pickPairFromDifferentCountries() throws Exception {
        List<Integer> peopleInCountries = Arrays.asList(2, 3, 1);
        assertThat(solution.pairsFromDifferentCountries(peopleInCountries)).isEqualTo(11);

    }

    @Test
    public void pickComboWithMoreThanAPair() throws Exception {
        List<Integer> peopleInCountries = Arrays.asList(2, 3, 1, 1);
        assertThat(solution.getNumberOfCombination(1, peopleInCountries)).isEqualTo(7);
        assertThat(solution.getNumberOfCombination(3, peopleInCountries)).isEqualTo(17);
    }


}
