/**
 * CS515 Assignment 5P
 *
 * Name: Huan Zhou(Rita)
 *
 * Section: 01
 *
 * Date: 06/05/2025
 *
 * Collaboration Declaration: 
 * Collaboration: None
 */

import PQueue.PQueue;

public class HuffTree {
    HuffNode _root;
     // Method expected by assignment (takes frequency array for a-z)
    public void buildTree(int[] freqs) {
        PQueue<HuffNode> pq = new PQueue<HuffNode>(HuffNode.class, freqs.length);
        
         // Add all characters with non-zero frequencies
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0) {
                char ch = (char)('a' + i);
                pq.insert(new HuffNode(null, null, freqs[i], ch));
            }
        }
        
         // Handle special case: only one character
        if (pq.size() == 1) {
            HuffNode single = pq.findMin();
            pq.deleteMin();
            _root = new HuffNode(single, null, single.freq, '\0');
            return;
        }
        
         // Build tree by combining nodes
        while (pq.size() > 1) {
            HuffNode left = pq.findMin();
            pq.deleteMin();
            HuffNode right = pq.findMin();
            pq.deleteMin();
            
            HuffNode parent = new HuffNode(left, right, left.freq + right.freq, '\0');
            pq.insert(parent);
        }
        
        _root = pq.findMin();
        pq.deleteMin();
    }

    public void buildTree(char[] chs, int[] freqs, int size){
        PQueue<HuffNode> pq = new PQueue<HuffNode>(HuffNode.class, size);
        
         // Add all characters to priority queue
        for (int i = 0; i < size; i++) {
            pq.insert(new HuffNode(null, null, freqs[i], chs[i]));
        }
        
         // Handle special case: only one character
        if (pq.size() == 1) {
            HuffNode single = pq.findMin();
            pq.deleteMin();
            _root = new HuffNode(single, null, single.freq, '\0');
            return;
        }
        
         // Build tree by combining nodes
        while (pq.size() > 1) {
            HuffNode left = pq.findMin();
            pq.deleteMin();
            HuffNode right = pq.findMin();
            pq.deleteMin();
            HuffNode parent = new HuffNode(left, right, left.freq + right.freq, '\0');
            pq.insert(parent);
        }
        
        _root = pq.findMin();
        pq.deleteMin();
    }

    public String getCode(char ch){
        if (_root == null) return "";
        return getCodeHelper(_root, ch, "");
    }

    private String getCodeHelper(HuffNode node, char ch, String code) {
        if (node == null) return null;
        
         // If leaf node, check if it's our character
        if (node.left == null && node.right == null) {
            if (node.data == ch) {
                 return code; // Return the accumulated code
            }
            return null;
        }
        
         // Search left subtree (add '0' to code)
        if (node.left != null) {
            String leftResult = getCodeHelper(node.left, ch, code + "0");
            if (leftResult != null) return leftResult;
        }
        
         // Search right subtree (add '1' to code)
        if (node.right != null) {
            String rightResult = getCodeHelper(node.right, ch, code + "1");
            if (rightResult != null) return rightResult;
        }
        
        return null;
    }

    private class HuffNode implements Comparable<HuffNode> {
        HuffNode left;
        HuffNode right;
        int freq;
        char data;
        
        public HuffNode(HuffNode left, HuffNode right, int freq, char data) {
            this.left = left;
            this.right = right;
            this.freq = freq;
            this.data = data;
        }

        @Override
        public int compareTo(HuffNode o) {
            return this.freq - o.freq;
        }
    }
}