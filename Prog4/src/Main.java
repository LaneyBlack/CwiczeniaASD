import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                Scanner input = new Scanner(new File(args[1]));
                PriorityQueue queue = new PriorityQueue();
                int mode = Integer.parseInt(args[0]);
                if (mode == 1) { // Mode Symbol + Number
                    while (input.hasNext())
                        queue.insert(new HuffmanNode(input.next().charAt(0), Integer.parseInt(input.next())));
                } else if (mode == 2) {
                    HashMap map = new HashMap<Character, Integer>();
                    while (input.hasNextLine()) {
                        String line = input.nextLine();
                        for (int i = 0; i < line.length(); i++) {
                            if (map.containsKey(line.charAt(i))) {
                                map.put(line.charAt(i), ((Integer) map.get(line.charAt(i))) + 1);
                            } else {
                                map.put(line.charAt(i), 1);
                            }
                        }
                    }
                    map.forEach((key, value) -> {
                        queue.insert(new HuffmanNode((char) key, (int) value));
                    });
                }
                while (queue.getSize() > 1)
                    queue.insert(new HuffmanNode(queue.pull(), queue.pull()));
                queue.generateHuffmanCode(queue.pull());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
