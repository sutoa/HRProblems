package org.nextgen.hackerranksolution.sparsearray;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Solution {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Solution solution = new Solution();
        final Map<String, Integer> words = solution.readWords(scanner);
        final List<String> queries = solution.readQueries(scanner);
        solution.printCounts(solution.findWordCount(words, queries));
    }

    protected Map<String, Integer> readWords(Scanner src) {
        final int numberOfStrings = src.nextInt();
        Map<String, Integer> wordCount = new HashMap<>(numberOfStrings);
        for (int i = 0; i < numberOfStrings; i++) {
            updateWordCount(wordCount, src.next());
        }

        return wordCount;
    }

    private void updateWordCount(Map<String, Integer> wordCount, String nextWord) {
        final Integer count = wordCount.get(nextWord);
        if (count == null) {
            wordCount.put(nextWord, new Integer(1));
        } else {
            wordCount.put(nextWord, count + 1);
        }
    }

    protected List<String> readQueries(Scanner src) {
        final int numberOfStrings = src.nextInt();
        List<String> queries = new ArrayList<>(numberOfStrings);
        for (int i = 0; i < numberOfStrings; i++) {
            queries.add(src.next());
        }

        return queries;
    }


    public List<Integer> findWordCount(Map<String, Integer> wordCounts, List<String> searchWords) {
        return searchWords.stream()
                .map(s -> {
                    final Integer count = wordCounts.get(s);
                    return count == null ? 0 : count;
                    })
                .collect(toList());
    }

    private void printCounts(List<Integer> counts) {
        counts.stream().forEach(integer -> System.out.println(integer));
    }
}
