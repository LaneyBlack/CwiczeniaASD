public class Tree {
    private Node root;
    private int countToSkip;

    private Node substitution;

    public Tree() {
    }

    public void build(Node newNode) {
        buildRec(root, null, newNode);
    }

    public void buildRec(Node currentNode, Node parent, Node newNode) {
        if (currentNode == null) //Root
            root = newNode;
        else { //Insert Node
            if (currentNode.getRightNode() != null)
                buildRec(currentNode.getRightNode(), currentNode, newNode);
            else
                currentNode.setRightNode(newNode);
            currentNode.updateHeight();
            currentNode.setSize(currentNode.getSize() + 1);
            rotationsCheck(currentNode, parent); //Rotations
        }
    }

    public void rotationsCheck(Node node, Node parent) {
        int tmp = getBalanceFactor(node);
        if (tmp < -1) {
            if (getBalanceFactor(node.getLeftNode()) > 0) { //Double Right (R-L)
                node = rotateRight(node.getRightNode(), node);
                rotateLeft(node, parent);
            } else //One rotation L
                rotateLeft(node, parent);
        } else if (tmp > 1) {
            if (getBalanceFactor(node.getRightNode()) < 0) { //Double Left (L-R)
                node = rotateLeft(node.getLeftNode(), node);
                rotateRight(node, parent);
            } else //One rotation R
                rotateRight(node, parent);
        }
    }

    public Node rotateRight(Node node, Node parent) {
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
        else if (parent.getLeftNode() == node)
            parent.setLeftNode(leftNode);
        else
            parent.setRightNode(leftNode);

        return leftNode;
    }

    public Node rotateLeft(Node node, Node parent) {
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
        else if (parent.getLeftNode() == node)
            parent.setLeftNode(rightNode);
        else
            parent.setRightNode(rightNode);
        return rightNode;
    }

    public void operateRec(Node currentNode, Node parent, int relativeIndex, int index) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > relativeIndex)
            operateRec(currentNode.getLeftNode(), currentNode, relativeIndex, index);
        else if (lTSize < relativeIndex)
            operateRec(currentNode.getRightNode(), currentNode, relativeIndex - lTSize - 1, index);
        else {
            Node tmp = currentNode.getRightNode();
            if (currentNode.getValue() % 2 == 0) { //DELETE
                System.out.println("Delete for: " + currentNode);
                if (tmp != null) //Deleted is in the same subtree
                    deleteRec(currentNode, parent, relativeIndex + 1);
                else //Deleted is in another subtree
                    deleteRec(root, null, (index + 1)%root.getSize());
            } else { //ADD
                System.out.println("Add  for: " + currentNode);
                Node newNode = new Node(currentNode.getValue() - 1);
                newNode.setRightNode(tmp);
                newNode.updateSizeAndHeight();
                currentNode.setRightNode(newNode);
                countToSkip = currentNode.getValue();
            }
        }
        currentNode.updateSizeAndHeight();
        rotationsCheck(currentNode, parent);
    }

    public int operate(int index) {
        operateRec(root, null, index, index);
        return countToSkip;
    }

    public void deleteRec(Node currentNode, Node parent, int index) {
        int lTSize = currentNode.getLeftNode() == null ? 0 : currentNode.getLeftNode().getSize();
        if (lTSize > index)
            deleteRec(currentNode.getLeftNode(), currentNode, index);
        else if (lTSize < index)
            deleteRec(currentNode.getRightNode(), currentNode, index - lTSize - 1);
        else {
            countToSkip=currentNode.getValue();
            getSubstitution(currentNode);
            if (substitution == null) {
                if (parent == null)
                    root = null;
                else if (parent.getLeftNode() == currentNode)
                    parent.setLeftNode(null);
                else
                    parent.setRightNode(null);
            } else {
                currentNode.setValue(substitution.getValue());
                substitution = null;
            }
        }
        currentNode.updateSizeAndHeight();
    }

    public Node getSubstitution(Node node) {
        if (node == null || !node.hasChildren())
            return null;
        if (node.getRightNode() != null)
            getSubstitutionRec(node.getRightNode(), node);
        else {
            substitution = node.getLeftNode();
            node.setLeftNode(null);
        }
        return substitution;
    }

    public void getSubstitutionRec(Node node, Node parent) {
        if (node.getLeftNode() != null)
            getSubstitutionRec(node.getLeftNode(), parent);
        else {
            parent.setLeftNode(node.getRightNode());
            substitution = node;
        }
        rotationsCheck(node, parent);
    }

    public Node getRoot() {
        return root;
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
