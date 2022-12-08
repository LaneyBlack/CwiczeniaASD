public class LetterTree {
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
//            System.out.println(word);
            if (longestWord.compareTo(word) < 0) {
                longestWord = word;
            }
        }
    }
}
