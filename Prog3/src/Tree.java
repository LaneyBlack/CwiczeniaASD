public class Tree {
    private Node root;
    private int countToSkip;

    public Tree() {
    }

    public void build(Node newNode) {
        buildRec(root, null, newNode);
    }

    public void buildRec(Node currentNode, Node prevNode, Node newNode) {
        if (currentNode == null) //Root
            root = newNode;
        else { //Insert Node
            if (currentNode.getRightNode() != null)
                buildRec(currentNode.getRightNode(), currentNode, newNode);
            else
                currentNode.setRightNode(newNode);
//            currentNode.setHeight(++globalHeight);
            currentNode.updateHeight();
            currentNode.setSize(currentNode.getSize() + 1);
            //Rotations
            rotationsCheck(currentNode, prevNode);
        }
    }

    public void rotationsCheck(Node node, Node prevNode) {
        int tmp = getBalanceFactor(node);
        if (tmp < -1) {
            if (getBalanceFactor(node.getLeftNode()) > 0) { //Double Right (R-L)
                node = rotateRight(node.getRightNode(), node);
                rotateLeft(node, prevNode);
            } else //One rotation L
                rotateLeft(node, prevNode);
        } else if (tmp > 1) {
            if (getBalanceFactor(node.getRightNode()) < 0) { //Double Left (L-R)
                node = rotateLeft(node.getLeftNode(), node);
                rotateRight(node, prevNode);
            } else //One rotation R
                rotateRight(node, prevNode);
        }
    }

    //ToDo Rotations if not root must be written...
    public Node rotateRight(Node node, Node prevNode) {
        System.out.println("Rotation right");
        Node leftNode = node.getLeftNode();
        Node rightLeftNode = leftNode.getRightNode();
        leftNode.setRightNode(node);
        node.setLeftNode(rightLeftNode);
        //Set heights
        node.updateSizeAndHeight();
        leftNode.updateSizeAndHeight();
        //Write down changes
        if (node == root)
            root = leftNode;
        else if (prevNode.getLeftNode() == node)
            prevNode.setLeftNode(leftNode);
        else
            prevNode.setRightNode(leftNode);

        return leftNode;
    }

    public Node rotateLeft(Node node, Node prevNode) {
        System.out.println("Rotation left");
        Node rightNode = node.getRightNode();
        Node leftRightNode = rightNode.getLeftNode();
        rightNode.setLeftNode(node);
        node.setRightNode(leftRightNode);
        //Set heights
        node.updateSizeAndHeight();
        rightNode.updateSizeAndHeight();
        //Write down changes
        if (node == root)
            root = rightNode;
        else if (prevNode.getLeftNode() == node)
            prevNode.setLeftNode(rightNode);
        else
            prevNode.setRightNode(rightNode);
        return rightNode;
    }

    public void operateRec(Node currentNode, Node prevNode, int relativeIndex, int index) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > relativeIndex)
            operateRec(currentNode.getLeftNode(), currentNode, relativeIndex, index);
        else if (lTSize < relativeIndex)
            operateRec(currentNode.getRightNode(), currentNode, relativeIndex - lTSize - 1, index);
        else {
            Node tmp = currentNode.getRightNode();
            if (currentNode.getValue() % 2 == 0) { //DELETE
                //ToDo substitution is always a node, that you found first
                System.out.println("Delete");
                deleteRec(root, null, index + 1, currentNode);
//                deleteRec(root, null, index + 1);
            } else { //ADD
                System.out.println("Add");
                Node newNode = new Node(currentNode.getValue() - 1);
                newNode.setRightNode(tmp);
                newNode.updateSizeAndHeight();
                currentNode.setRightNode(newNode);
                countToSkip = currentNode.getValue();
            }
        }
        currentNode.updateSizeAndHeight();
        rotationsCheck(currentNode, prevNode);
    }

    public int operate(int index) {
        operateRec(root, null, index, index);
        return countToSkip;
    }

    //ToDo Do this without prevNode
    public void deleteRec(Node currentNode, Node prevNode, int index, Node substitution) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > index)
            deleteRec(currentNode.getLeftNode(), currentNode, index, substitution);
        else if (lTSize < index)
            deleteRec(currentNode.getRightNode(), currentNode, index - lTSize - 1, substitution);
        else {
            //ToDo Operation
        }
    }

//    public void deleteRec(Node currentNode, Node prevNode, int index) {
//        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
//        if (lTSize > index)
//            deleteRec(currentNode.getLeftNode(), currentNode, index);
//        else if (lTSize < index)
//            deleteRec(currentNode.getRightNode(), currentNode, index - lTSize - 1);
//        else {
//            Node sub = getSubstitution(currentNode);
//            sub.setRightNode(currentNode.getRightNode());
//            sub.setLeftNode(currentNode.getLeftNode());
//            if (currentNode == root)
//                root = sub;
//            else
//                prevNode.setRightNode(sub);
//            countToSkip = currentNode.getValue();
//        }
//        currentNode.updateSizeAndHeight();
//        rotationsCheck(currentNode);
//    }

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

//    public Node getSubstitution(Node node) {
//        if (node == null || !node.hasChildren())
//            return node;
//        if (node.getLeftNode() != null)
//            return cutMax(node.getLeftNode(), null);
//        else
//            return node.getRightNode();
//    }
//
//    public Node cutMax(Node node, Node prev) {
//        if (node.getRightNode() != null)
//            return cutMax(node.getRightNode(), node);
//        prev.setRightNode(node.getLeftNode());
//        return node;
//    }

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
