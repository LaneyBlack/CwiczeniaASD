public class Tree {
    private Node root;
    private int opCount;
    private int index;
    private int globalHeight;

    public Tree(int opCount) {
        this.opCount = opCount;
        globalHeight = 0;
    }

    public void printRec(Node tmp) {
        System.out.println(tmp);
        if (tmp.getLeftNode() != null)
            printRec(tmp.getLeftNode());
        if (tmp.getRightNode() != null)
            printRec(tmp.getRightNode());
    }

    public void build(Node newNode) {
        add(root, newNode);
    }

    public void add(Node curNode, Node newNode) {
        if (curNode == null) //Root
            root = newNode;
        else { //Insert Node
            globalHeight = 1;
            if (curNode.getRightNode() != null) {
                add(curNode.getRightNode(), newNode);
            } else {
                curNode.setRightNode(newNode);
            }
            curNode.setHeight(++globalHeight);
            //Rotations
            if (getBalanceFactor(curNode) < -1) {
                if (getBalanceFactor(curNode.getLeftNode()) > 0) { //Double Right Or R-L rotation
                    //ToDo
                    curNode = rotateRight(curNode);
                    curNode = rotateLeft(curNode);
                } else //One rot
                    curNode = rotateLeft(curNode);
            }
        }
    }

    public void operate() {
        while (opCount > 0) {

            opCount--;
        }
    }

    public Node rotateLeft(Node node) {
        System.out.println("Rotation left");
        Node rightNode = node.getRightNode();
        Node leftRightNode = rightNode.getLeftNode();
        rightNode.setLeftNode(node);
        node.setRightNode(leftRightNode);
        //Set heights
        int tmp;
        //For A
        tmp = Math.max(node.getLeftNode() == null ? 0 : node.getLeftNode().getHeight(),
                node.getRightNode() == null ? 0 : node.getRightNode().getHeight());
        node.setHeight(tmp + 1);
        //For B
        tmp = Math.max(rightNode.getLeftNode() == null ? 0 : rightNode.getLeftNode().getHeight(),
                rightNode.getRightNode() == null ? 0 : rightNode.getRightNode().getHeight());
        rightNode.setHeight(tmp + 1);
        if (node == root)
            root = rightNode;
        return rightNode;
    }

    public Node rotateRight(Node node) {
        System.out.println("Rotation right");
        Node leftNode = node.getLeftNode();
        Node rightLeftNode = leftNode.getRightNode();
        leftNode.setRightNode(node);
        node.setLeftNode(rightLeftNode);
        //Set heights
        int tmp;
        //For A
        tmp = Math.max(node.getLeftNode() == null ? 0 : node.getLeftNode().getHeight(),
                node.getRightNode() == null ? 0 : node.getRightNode().getHeight());
        node.setHeight(tmp + 1);
        //For B
        tmp = Math.max(leftNode.getLeftNode() == null ? 0 : leftNode.getLeftNode().getHeight(),
                leftNode.getRightNode() == null ? 0 : leftNode.getRightNode().getHeight());
        leftNode.setHeight(tmp + 1);
        if (node == root)
            root = leftNode;
        return leftNode;
    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        int result = 0;
        if (node.getLeftNode() != null)
            result += node.getLeftNode().getHeight();
        if (node.getRightNode() != null)
            result -= node.getRightNode().getHeight();
        return result;
    }

    public Node getRoot() {
        return root;
    }
}
