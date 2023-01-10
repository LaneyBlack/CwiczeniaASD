public class Tree {
    private Node root;
    private int countToSkip;
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

    public void rotationsCheck(Node node) {
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

    //ToDo Rotations if not root must be written...
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

    public void operateRec(Node currentNode, int relIndex, int index) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > relIndex)
            operateRec(currentNode.getLeftNode(), relIndex, index);
        else if (lTSize < relIndex)
            operateRec(currentNode.getRightNode(), relIndex - lTSize - 1, index);
        else {
            Node tmp = currentNode.getRightNode();
            if (currentNode.getValue() % 2 == 0) { //DELETE
                //ToDo substitution is always a node, that you found first
                System.out.println("Delete");
                deleteRec(root, null, index + 1);
            } else { //ADD
                System.out.println("Add");
                Node newNode = new Node(currentNode.getIndex(), currentNode.getValue() - 1);
                newNode.setRightNode(tmp);
                newNode.updateSizeAndHeight();
                currentNode.setRightNode(newNode);
                countToSkip = currentNode.getValue();
            }
        }
        currentNode.updateSizeAndHeight();
        rotationsCheck(currentNode);
    }

    public int operate(int index) {
        operateRec(root, index, index);
        return countToSkip;
    }

    public void deleteRec(Node currentNode, Node prevNode, int index) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > index)
            deleteRec(currentNode.getLeftNode(), currentNode, index);
        else if (lTSize < index)
            deleteRec(currentNode.getRightNode(), currentNode, index - lTSize - 1);
        else {
            Node sub = getSubstitution(currentNode);
            sub.setRightNode(currentNode.getRightNode());
            sub.setLeftNode(currentNode.getLeftNode());
            if (currentNode == root)
                root = sub;
            else
                prevNode.setRightNode(sub);
            countToSkip = currentNode.getValue();
        }
        currentNode.updateSizeAndHeight();
        rotationsCheck(currentNode);
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

    public Node getSubstitution(Node node) {
        if (node == null || !node.hasChildren())
            return node;
        if (node.getLeftNode() != null)
            return cutMax(node.getLeftNode(), null);
        else
            return node.getRightNode();
    }

    public Node cutMax(Node node, Node prev) {
        if (node.getRightNode() != null)
            return cutMax(node.getRightNode(), node);
        prev.setRightNode(node.getLeftNode());
        return node;
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
