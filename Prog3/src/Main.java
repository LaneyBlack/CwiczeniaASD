import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                int operationCount = Integer.parseInt(input.next().trim());
                Tree tree = new Tree();
                int index = 0, operation = 1;
                while (input.hasNext()) {
                    System.out.println(index + ": ");
                    tree.build(new Node(Integer.parseInt(input.next().replace(",", "").trim())));
                    tree.print(tree.getRoot());
                    index++;
                }
                index = 0;
                while (operation <= operationCount) {
                    System.out.println("Operations: " + operation + " ---------------------------------------------");
                    if (tree.getRoot() != null) {
                        index %= tree.getRoot().getSize();
                        System.out.println(tree.printAnswer(index));
                        index += tree.operate(index);
                    }
                    tree.print(tree.getRoot());
                    operation++;
                }
                System.out.println(tree.printAnswer(index));
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Wrong input exception");
        }
    }
}
