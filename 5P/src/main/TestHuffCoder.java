import java.io.IOException;

public class TestHuffCoder {
    public static void main(String[] args) {
        try {
            HuffCoder coder = new HuffCoder();
            coder.encode("input1");
            
            System.out.println("Original bits: " + coder.originalBits);
            System.out.println("Compressed bits: " + coder.compressedBits);
            System.out.println("Compression ratio: " + String.format("%.2f", coder.compressionRatio) + "%");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 