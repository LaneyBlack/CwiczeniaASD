import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                Tree tree = new Tree(Integer.parseInt(input.next().trim()));
                int index = 0;
                while (input.hasNext()) {
                    System.out.println(index + ": ");
                    tree.build(new Node(index++, Integer.parseInt(input.next().trim())));
                    tree.printRec(tree.getRoot());
                }
//                tree.operate();
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Wrong input exception");
        }
    }
}
