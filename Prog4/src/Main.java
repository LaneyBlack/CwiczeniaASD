import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                PriorityQueue queue = new PriorityQueue();
                while(input.hasNext())
                    queue.write(new Node(input.next(), Integer.parseInt(input.next())));

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
