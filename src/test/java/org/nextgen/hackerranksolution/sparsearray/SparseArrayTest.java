package org.nextgen.hackerranksolution.sparsearray;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SparseArrayTest {
    private Solution solution  = new Solution();

    @Test
    public void readWords() throws Exception {
        String input = "2\nabc\nefg";
        final Map<String, Integer> stringsMultiSet = solution.readWords(new Scanner(input));
        assertThat(stringsMultiSet.size()).isEqualTo(2);
        assertThat(stringsMultiSet.get("abc")).isEqualTo(new Integer(1));
        assertThat(stringsMultiSet.get("efg")).isEqualTo(new Integer(1));
    }

    @Test
    public void readQueries() throws Exception {
        String input = "2\nabc\nefg";
        final List<String>  queries = solution.readQueries(new Scanner(input));
        assertThat(queries.size()).isEqualTo(2);
        assertThat(queries.contains("abc")).isTrue();
        assertThat(queries.contains("efg")).isTrue();
    }

    @Test
    public void findWordCount() throws Exception {
        Map<String, Integer> wordCounts = new HashMap<>(4);
        wordCounts.put("john", 1);
        wordCounts.put("steve", 3);
        wordCounts.put("peter", 4);

        List<String> searchWords = Arrays.asList("peter", "tong");
        List<Integer> expectedCounts = Arrays.asList(4, 0);

        assertThat(solution.findWordCount(wordCounts, searchWords)).isEqualTo(expectedCounts);



    }
}
