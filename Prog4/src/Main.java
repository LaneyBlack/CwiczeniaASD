import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                PriorityQueue queue = new PriorityQueue();
                while(input.hasNext())
                    queue.insert(new HuffmanNode(input.next().charAt(0), Integer.parseInt(input.next())));
                queue.print();
                while (queue.getSize()>1){
                    queue.insert(new HuffmanNode(queue.pull(), queue.pull()));
                }
                queue.generateHuffmanCode(queue.pull(), "");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
