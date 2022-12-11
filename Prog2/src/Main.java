import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length==1) {
            LetterTree tree = new LetterTree();
            Scanner input = new Scanner(new File(args[0]));
            String[] values;
            while (input.hasNextLine()) {
                values = input.nextLine().trim().split(" ");
                if (values.length == 2)
                    tree.build(values[0].charAt(0), values[1]);
                else if (values.length==1 && values[0].length()>0)
                    tree.build(values[0].charAt(0), "");
            }
            System.out.println(tree.getLongestWord());
        } else {
            System.out.println("Wrong arguments. Expected only src to txt file.");
        }
    }
}