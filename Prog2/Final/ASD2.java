import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ASD2 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            LetterTree tree = new LetterTree();
            Scanner input = new Scanner(new File(args[0])); //Scanner on file
            String values[];
            while (input.hasNextLine()) {
                values = input.nextLine().trim().split(" ");
                if (values.length == 2)
                    tree.build(values[0].charAt(0), values[1]); //build using a value and path
                else
                    tree.build(values[0].charAt(0), "");
            }
            System.out.println(tree.getLongestWord());
        } else
            System.out.println("Wrong arguments. Expected only src to txt file.");
    }
}

class LetterNode {
    private char value;
    private LetterNode leftNode;
    private LetterNode rightNode;

    public LetterNode() {
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public LetterNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(LetterNode leftNode) {
        this.leftNode = leftNode;
    }

    public LetterNode getRightNode() {
        return rightNode;
    }

    public boolean hasChildren() {
        return leftNode != null || rightNode != null;
    }
    public void setRightNode(LetterNode rightNode) {
        this.rightNode = rightNode;
    }
}

class LetterTree {
    private LetterNode head;
    private String longestWord;

    public LetterTree() {
        head = null;
        longestWord = "";
    }

    public void build(char value, String steps) {
        if (head == null)
            head = new LetterNode();
        LetterNode tmp = head;
        for (int i = 0; i < steps.length(); i++) {
            if (steps.charAt(i) == 'R') {
                if (tmp.getRightNode() == null)
                    tmp.setRightNode(new LetterNode());
                tmp = tmp.getRightNode();
            } else {
                if (tmp.getLeftNode() == null)
                    tmp.setLeftNode(new LetterNode());
                tmp = tmp.getLeftNode();
            }
        }
        tmp.setValue(value);
    }

    public String getLongestWord() {
        findWordRec(head, "");
        return longestWord;
    }

    public void findWordRec(LetterNode tmp, String word) {
        word = tmp.getValue() + word;
        if (tmp.getLeftNode() != null)
            findWordRec(tmp.getLeftNode(), word);
        if (tmp.getRightNode() != null)
            findWordRec(tmp.getRightNode(), word);
        if (!tmp.hasChildren()) {
            if (longestWord.compareTo(word) < 0) {
                longestWord = word;
            }
        }
    }
}