import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.javatuples.Pair;

public class Climber {
    public static int climb(Pair<Integer, Integer> startPost, Pair<Integer, Integer> endPost,
            List<Pair<Integer, Integer>> posts) {

        // Create a map to track the minimum maximum rope weight needed to reach each post
        Map<Pair<Integer, Integer>, Integer> minMaxRopeWeight = new HashMap<>();
        
        // Priority queue to process posts in order of minimum maximum rope weight
        // Stores pairs of (ropeWeight, post)
        PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> pq = 
            new PriorityQueue<>((a, b) -> Integer.compare(a.getValue0(), b.getValue0()));
        
        // Set to track visited posts
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        
        // Initialize distances - start post requires 0 rope weight
        for (Pair<Integer, Integer> post : posts) {
            minMaxRopeWeight.put(post, Integer.MAX_VALUE);
        }
        minMaxRopeWeight.put(startPost, 0);
        
        // Add start post to priority queue
        pq.offer(new Pair<>(0, startPost));
        
        while (!pq.isEmpty()) {
            Pair<Integer, Pair<Integer, Integer>> current = pq.poll();
            int currentMaxRopeWeight = current.getValue0();
            Pair<Integer, Integer> currentPost = current.getValue1();
            
            // Skip if already visited
            if (visited.contains(currentPost)) {
                continue;
            }
            
            visited.add(currentPost);
            
            // If we reached the end post, return the result
            if (currentPost.equals(endPost)) {
                return currentMaxRopeWeight;
            }
            
            // Check all other posts as potential next destinations
            for (Pair<Integer, Integer> nextPost : posts) {
                if (visited.contains(nextPost)) {
                    continue;
                }
                
                // Calculate rope weight needed for this jump (distance squared)
                int dx = nextPost.getValue0() - currentPost.getValue0();
                int dy = nextPost.getValue1() - currentPost.getValue1();
                int ropeWeightForJump = dx * dx + dy * dy;
                
                // The maximum rope weight for this path is the max of:
                // 1. The current maximum rope weight to reach currentPost
                // 2. The rope weight needed for this jump
                int newMaxRopeWeight = Math.max(currentMaxRopeWeight, ropeWeightForJump);
                
                // If this path to nextPost is better, update it
                if (newMaxRopeWeight < minMaxRopeWeight.get(nextPost)) {
                    minMaxRopeWeight.put(nextPost, newMaxRopeWeight);
                    pq.offer(new Pair<>(newMaxRopeWeight, nextPost));
                }
            }
        }
        
        // If we can't reach the end post, return -1
        return -1;
    }
}
