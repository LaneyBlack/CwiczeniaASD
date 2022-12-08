public class LetterTree {
    private LetterNode head;
    private int size;

    public LetterTree() {
        head = null;
        size = 0;
    }

    public void build(char value, String steps) {
        if (head == null)
            head = new LetterNode();
        LetterNode tmp = head;
        for (int i = 0; i < steps.length(); i++) {
            if(steps.charAt(i)=='R'){
                if (tmp.getRightNode()==null)
                    tmp.setRightNode(new LetterNode());
                tmp = tmp.getRightNode();
            }else{
                if (tmp.getLeftNode()==null)
                    tmp.setLeftNode(new LetterNode());
                tmp = tmp.getLeftNode();
            }
        }
        tmp.setValue(value);
        size++;
    }
    public void printInTree(LetterNode tmp){
        if (tmp.getLeftNode()!=null)
            printInTree(tmp.getLeftNode());
        System.out.print(tmp.getValue());
        if (tmp==head)
            System.out.println();
        if (tmp.getRightNode()!=null) {
            printInTree(tmp.getRightNode());
        }
    }

    public LetterNode getHead() {
        return head;
    }
}
