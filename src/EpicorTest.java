import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class EpicorTest {

    private static final Set<String> EXCLUDED_WORDS = Set.of(
            "ours", "me", "us", "him", "her", "them", "about", "above", "across", "against", "along", "among", "around", "behind", "below",
            "beneath", "beside", "between", "beyond", "concerning", "despite", "down", "during", "except", "for", "from", "in", "inside", "into",
            "like", "near", "of", "off", "on", "onto", "out", "outside", "over", "past", "regarding", "since", "through", "throughout", "to", "toward",
            "under", "underneath", "until", "up", "upon", "with", "within", "without", "there", "had",
            "and", "or", "but", "nor", "so", "yet", "although", "because", "unless", "while", "after", "before", "whenever", "if", "though",
            "even though", "whereas", "whether", "provided that", "in case", "myself", "yourself", "herself", "himself", "oneself", "itself",
            "ourselves", "themselves", "yourselves", "mine", "yours", "his", "hers", "theirs", "its", "whose", "who", "whom", "what", "when",
            "why", "where", "this", "that", "these", "those", "anyone", "everyone", "something", "nothing", "i", "you", "we", "they",
            "at", "he", "she", "it", "the", "a", "an", "is", "was", "are", "were", "by", "as", "can", "could", "may", "might", "must", "shall",
            "should", "will", "would", "ought", "all", "ought to", "need to", "dare", "not", "be", "then","which"
    );

    public static void main(String[] args) throws IOException {
        var start = System.currentTimeMillis();
        URI uri = new File("resource/moby.txt").toURI();
        String text = Files.readString(Path.of(uri));
        analyzeText(text);
        var end = System.currentTimeMillis();
        System.out.println("total time to execute the program is: " + Math.subtractExact(end, start)+" ms");

    }

    /**
     * This method is used to analyze the text
     * @param text
     */
    public static void analyzeText(String text) {
        // Normalize the text by converting it to lowercase and splitting into words
        String[] words = text.toLowerCase().replaceAll("[^a-z\s]", "").split("\\s+");

        // Filter the words based on exclusions
        List<String> filteredWords = Arrays.stream(words)
                .filter(word -> !EXCLUDED_WORDS.contains(word) && !word.endsWith("s") && !word.isEmpty())
                .toList();

        int totalWordCount = filteredWords.size();
        System.out.println("Total no. of Word Count: " + totalWordCount);

        // Calculating the word frequencies
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : filteredWords) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("List of 5 Most Frequent Words:");
        List<Map.Entry<String, Integer>> topWords = wordFrequency.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .toList();

        for (Map.Entry<String, Integer> entry : topWords) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Get unique words and sort them alphabetically
        System.out.println("Top 50 Unique Words");
        filteredWords.stream()
                .distinct()
                .sorted()
                .limit(50)
                .toList().forEach(System.out::println);

    }
}
