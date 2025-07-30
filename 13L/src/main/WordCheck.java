import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class WordCheck {
    // You are allowed to add private methods, or private members but you cannot
    // alter the return or parameter type of either the following public
    // methods.

    // Pattern-based data structure: pattern -> set of words matching that pattern
    // For example: "_ine" -> {"wine", "dine", "fine", "line", "mine", ...}
    private Map<String, Set<String>> patternToWords;

    /**
     * @param dictionary
     */
    public WordCheck(Iterator<String> dictionary) {
        patternToWords = new HashMap<>();
        
        // Process all words from the dictionary
        while (dictionary.hasNext()) {
            String word = dictionary.next().toLowerCase().trim();
            
            // Filter: only 4-letter words with alphabetic characters only
            if (word.length() == 4 && word.matches("[a-z]+")) {
                // Generate all 4 patterns for this word (replace each position with '_')
                for (int i = 0; i < 4; i++) {
                    String pattern = word.substring(0, i) + "_" + word.substring(i + 1);
                    
                    // Add this word to the set for this pattern
                    patternToWords.computeIfAbsent(pattern, k -> new HashSet<>()).add(word);
                }
            }
        }
    }

    /**
     * @param query
     * @return Set<alteredWords>
     */
    public Set<String> wordCheck(String query) {
        // Validate input length
        if (query.length() != 4) {
            throw new IllegalArgumentException("Query must be exactly 4 characters");
        }
        
        query = query.toLowerCase();
        Set<String> result = new HashSet<>();
        
        // Generate all 4 patterns for the query word
        for (int i = 0; i < 4; i++) {
            String pattern = query.substring(0, i) + "_" + query.substring(i + 1);
            
            // O(1) lookup - no scanning through entire dictionary!
            Set<String> matchingWords = patternToWords.get(pattern);
            
            if (matchingWords != null) {
                result.addAll(matchingWords);
            }
        }
        
        // Remove the original query word (we want words that differ, not same word)
        result.remove(query);
        
        return result;
    }
}
