import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.SortedMap;
import org.javatuples.Pair;
import org.javatuples.Triplet;

public class WordFreq {
    // You are allowed to add private methods, or private members but you cannot
    // alter the return or parameter type of either the following public
    // methods.

    // Private data structure: word -> (year -> frequency)
    private Map<String, TreeMap<Integer, Integer>> wordData;

    /**
     * @param dict
     *             dict is an iterator of triplets which stores
     *             String = word
     *             Integer0 = year
     *             Integer1 = frequency
     */
    public WordFreq(Iterator<Triplet<String, Integer, Integer>> dictionary) {
        // Initialize the main data structure
        wordData = new HashMap<>();
        
        // Process all entries from the iterator
        while (dictionary.hasNext()) {
            Triplet<String, Integer, Integer> entry = dictionary.next();
            String word = entry.getValue0();      // word
            Integer year = entry.getValue1();     // year
            Integer frequency = entry.getValue2(); // frequency
            
            // Get or create the TreeMap for this word
            TreeMap<Integer, Integer> yearFreqMap = wordData.get(word);
            if (yearFreqMap == null) {
                yearFreqMap = new TreeMap<>();
                wordData.put(word, yearFreqMap);
            }
            
            // Store year -> frequency mapping
            yearFreqMap.put(year, frequency);
        }
    }

    /**
     * @param query
     * @param startYear
     * @return Set<Pair<year, frequency>>
     */
    public Set<Pair<Integer, Integer>> wordFreq(String query, int startYear) {
        Set<Pair<Integer, Integer>> result = new HashSet<>();
        
        // O(1) word lookup - no loop through entire dictionary!
        TreeMap<Integer, Integer> yearFreqMap = wordData.get(query);
        
        if (yearFreqMap != null) {
            // Get all years >= startYear using TreeMap's efficient range query
            SortedMap<Integer, Integer> relevantYears = yearFreqMap.tailMap(startYear);
            
            // Only loop needed: building the result set from filtered years
            for (Map.Entry<Integer, Integer> entry : relevantYears.entrySet()) {
                result.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
        }
        
        return result;
    }
}
