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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class HuffCoder {
    int originalBits;
    int compressedBits;
    float compressionRatio;

    public void encode(String filename) throws IOException {
        // Initialize frequency array for a-z (26 characters)
        int[] freqs = new int[26];
        int totalChars = 0;
        
        Path path = Paths.get("src", "main", "input_files", filename);
        BufferedReader file = new BufferedReader(new FileReader(path.toFile()));
        String line = file.readLine();
        
        // Count character frequencies
        while (line != null) {
            for (char c : line.toCharArray()) {
                if (c == '\r') continue; // Ignore carriage return
                
                if (Character.isLetter(c)) {
                    // Convert to lowercase and count
                    char lower = Character.toLowerCase(c);
                    freqs[lower - 'a']++;
                    totalChars++;
                } else {
                    // Non-alphabetic characters count as 8 bits each
                    totalChars++;
                }
            }
            // Count newline character if there are more lines to read
            line = file.readLine();
            if (line != null) {
                totalChars++;
            }
        }
        file.close();
        
        // Calculate original bits (8 bits per character)
        this.originalBits = totalChars * 8;
        
        // Build Huffman tree
        HuffTree tree = new HuffTree();
        tree.buildTree(freqs);
        
        // Calculate compressed bits
        this.compressedBits = 0;
        for (int i = 0; i < 26; i++) {
            if (freqs[i] > 0) {
                char ch = (char)('a' + i);
                String code = tree.getCode(ch);
                this.compressedBits += freqs[i] * code.length();
            }
        }
        
        // Add 8 bits for each non-alphabetic character (they don't get compressed)
        int nonAlphaCount = totalChars - getAlphabeticCount(freqs);
        this.compressedBits += nonAlphaCount * 8;
        
        // Calculate compression ratio
        if (this.originalBits > 0) {
            this.compressionRatio = 100.0f * (this.originalBits - this.compressedBits) / this.originalBits;
        } else {
            this.compressionRatio = 0.0f;
        }
    }
    
    private int getAlphabeticCount(int[] freqs) {
        int count = 0;
        for (int freq : freqs) {
            count += freq;
        }
        return count;
    }
}
