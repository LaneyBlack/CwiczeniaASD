import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LetterTree tree = new LetterTree();
        Scanner input = new Scanner(new File(args[0]));
        String values[];
        while (input.hasNextLine()) {
            values = input.nextLine().trim().split(" ");
            if (values.length == 2)
                tree.build(values[0].charAt(0), values[1]);
            else
                tree.build(values[0].charAt(0), "");
        }
        tree.printInTree(tree.getHead());
    }
}