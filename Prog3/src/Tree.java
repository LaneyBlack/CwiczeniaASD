public class Tree {
    private Node root;
    private Node currentNode;
    private int globalHeight;

    public Tree() {
        globalHeight = 0;
    }

    public void build(Node newNode) {
        add(root, newNode);
    }

    public void add(Node currentNode, Node newNode) {
        if (currentNode == null) //Root
            root = newNode;
        else { //Insert Node
            globalHeight = 1;
            if (newNode.getIndex() >= currentNode.getIndex())
                if (currentNode.getRightNode() != null) {
                    add(currentNode.getRightNode(), newNode);
                } else {
                    currentNode.setRightNode(newNode);
                }
            else if (currentNode.getLeftNode() != null) {
                add(currentNode.getLeftNode(), newNode);
            } else {
                currentNode.setLeftNode(newNode);
            }
            currentNode.setHeight(++globalHeight);
            currentNode.setSize(currentNode.getSize() + 1);
            //Rotations
            rotationsCheck(currentNode);
        }
    }

    public void rotationsCheck(Node node){
        int tmp = getBalanceFactor(node);
        if (tmp < -1) {
            if (getBalanceFactor(node.getLeftNode()) > 0) { //Double Right (R-L)
                node = rotateRight(node.getRightNode());
                rotateLeft(node);
            } else //One rotation L
                rotateLeft(node);
        } else if (tmp > 1) {
            if (getBalanceFactor(node.getRightNode()) < 0) { //Double Left (L-R)
                node = rotateLeft(node.getLeftNode());
                rotateRight(node);
            } else //One rotation R
                rotateRight(node);
        }
    }

    public Node rotateLeft(Node node) {
        System.out.println("Rotation left");
        Node rightNode = node.getRightNode();
        Node leftRightNode = rightNode.getLeftNode();
        rightNode.setLeftNode(node);
        node.setRightNode(leftRightNode);

        //Set heights
        node.updateSizeAndHeight();
        rightNode.updateSizeAndHeight();

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
        node.updateSizeAndHeight();
        leftNode.updateSizeAndHeight();
        if (node == root)
            root = leftNode;
        return leftNode;
    }

    public void operateRec(Node currentNode, int index) {
        //ToDo return int to skip...
        int lTSize = currentNode.getLeftNode().getSize();
        if (lTSize > index) {
            operateRec(currentNode.getLeftNode(), index);
        } else if (lTSize < index) {
            operateRec(currentNode.getRightNode(), index - currentNode.getLeftNode().getSize());
        } else {
            Node tmp = currentNode.getRightNode();
            if (currentNode.getValue() % 2 == 0) { //DELETE
            } else { //ADD
                Node newNode = new Node(currentNode.getIndex(), currentNode.getValue() - 1);
                newNode.setRightNode(tmp);
                newNode.updateSizeAndHeight();
                currentNode.setRightNode(newNode);
                currentNode.setSize(currentNode.getSize() + 1);
                currentNode.setHeight(currentNode.getHeight() + 1);
            }
        }
        rotationsCheck(currentNode);
    }

    public void deleteNext(Node currentNode, int index) {

    }

    public Node operate(int index) {
        return operateRec(root, index);
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

    public void print(Node start) {
        printRec(start, 0);
    }

    public void printRec(Node tmp, int space) {
        if (tmp == null)
            return;
        space += 2;
        printRec(tmp.getRightNode(), space);
        for (int i = 0; i < space - 2; i++)
            System.out.print("\t");
        System.out.print(tmp + "\n");
        printRec(tmp.getLeftNode(), space);
    }
}
