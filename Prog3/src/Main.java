import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                int operationCount = Integer.parseInt(input.next().trim());
                Tree tree = new Tree();
                int index = 0;
                while (input.hasNext()) {
                    System.out.println(index + ": ");
                    tree.build(new Node(index++, Integer.parseInt(input.next().trim())));
                    tree.print(tree.getRoot());
                }
                index = 0;
                while (operationCount > 0) {
                    System.out.println("Operations: " + operationCount);
                    index %= tree.getRoot().getSize();
                    index += tree.operate(index);
                    operationCount--;
                    tree.print(tree.getRoot());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Wrong input exception");
        }
    }
}
