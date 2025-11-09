import com.sun.source.tree.Tree;
import java.util.*;
import javax.crypto.Mac;

public class NLPUtility {

    /**
     * Splits the given text into word tokens using one or more whitespace
     * or punctuation characters as delimiters.
     *
     * @param text the input string to be tokenized
     * @return an array of word tokens, excluding punctuation and whitespace
     */
    public static String[] splitTextIntoTokens(String text) {
        ArrayList<String> names = new ArrayList<>();
        if(text.length()>0){
            names.addAll(Arrays.asList(text.split("[\\s\\p{P}]+")));
            return names.toArray(new String[names.size() -1]);
            }
        else{
            System.err.println("Error tokenizing input, Please try again!");
            return null;
        }
        
    }

    /**
     * Counts the frequency of words in the given array, excluding those present in
     * the specified set of stop words.
     * The comparison is case-insensitive, and results are stored in a
     * {@link TreeMap} sorted alphabetically by word.
     *
     * @param words     An array of tokenized words to analyze.
     * @param stopWords A set of words to exclude from the frequency count (e.g.,
     *                  common stop words like "the", "and").
     * @return A {@link TreeMap} mapping each non-stop word to its frequency, sorted
     *         alphabetically.
     */
    public static TreeMap<String, Integer> countFilteredWords(String[] words, Set<String> stopWords) {
        
        TreeMap<String, Integer> wordCount = new TreeMap<>();
        for(String word: words){
            String lowerWord = word.toLowerCase();
            if(!stopWords.contains(lowerWord)){
                wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);
            }
        }
        return wordCount;
    }

    /**
     * Sorts the entries of a map by their values in descending order.
     * The result is returned as a {@link LinkedHashMap} to preserve the order of
     * sorted entries.
     *
     * @param map A map containing keys and integer values to be sorted by value.
     * @return A {@link LinkedHashMap} containing the same entries as the input map,
     *         sorted in descending order by value.
     */
    public static LinkedHashMap<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        Map<String, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(
                        LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll);
        return (LinkedHashMap<String, Integer>) sortedMap;
    }

    /**
     * Performs sentiment analysis by scanning the word-frequency map.
     * Adds up the total frequency of all words found in the predefined
     * positive and negative word sets.
     *
     * @param wordMap A map of words and their frequencies.
     * @return A summary string in the format: "Positive: X, Negative: Y"
     *         where X and Y are the total counts of positive and negative words.
     */
    public static String getSentiment(Map<String, Integer> wordMap, Set<String> positiveWords,
            Set<String> negativeWords) {
        
                Map<String, Integer> sentimentCount = new HashMap<>();
            int positiveCount = 0;
            int negativeCount = 0;
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                if (positiveWords.contains(word)) {
                    positiveCount += frequency;
                } else if (negativeWords.contains(word)) {
                    negativeCount += frequency;
                }
            }
            sentimentCount.put("Positive", positiveCount);
            sentimentCount.put("Negative", negativeCount);
        return "Positive: " + positiveCount + ", Negative: " + negativeCount;
    }

    /**
     * Finds the words with the highest frequency in the given map and returns a map
     * containing a sorted word list along with the maximum frequency value.
     *
     * @param wordMap A map of words and their corresponding frequencies.
     * @return A map containing:
     *         - "words": A list of words with the highest frequency, sorted
     *         alphabetically.
     *         - "frequency": The highest frequency value.
     */
    public static Map<String, Object> getWordsWithMaxFrequency(Map<String, Integer> wordMap) {
    
        if (wordMap == null || wordMap.isEmpty()) {
            return null;
        }
        int maxFrequency = Collections.max(wordMap.values());
        List<String> mostFrequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mostFrequentWords.add(entry.getKey());
            }
        }
        Collections.sort(mostFrequentWords);
        Map<String, Object> result = new HashMap<>();
        return Map.of(
            "words", mostFrequentWords,
            "frequency", maxFrequency
        );
    }

}
