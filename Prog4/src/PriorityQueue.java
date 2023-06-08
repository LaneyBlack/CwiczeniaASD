public class PriorityQueue {
    private HuffmanNode head;
    private int size;

    public PriorityQueue() {
        size = 0;
    }

    public void insert(HuffmanNode node) {
        size++;
        if (head == null)
            head = node;
        else {
            if (node.compareTo(head) < 0) {
                node.setNext(head);
                head = node;
            } else {
                HuffmanNode current = head.getNext(), previous = head;
                while (current != null && node.compareTo(current) > 0) {
                    previous = current;
                    current = current.getNext();
                }
                node.setNext(current);
                previous.setNext(node);
            }
        }
    }

    public void generateHuffmanCode(HuffmanNode node) {
        if (node.hasChildren())
            generateHuffmanCodeRec(node, "");
        else
            System.out.println(node.getCharacter() + " 0");
    }

    public void generateHuffmanCodeRec(HuffmanNode node, String code) {
        if (node.getLeft() != null)
            generateHuffmanCodeRec(node.getLeft(), code.concat("0"));
        if (node.getRight() != null)
            generateHuffmanCodeRec(node.getRight(), code.concat("1"));
        if (!node.hasChildren())
            System.out.println(node.getCharacter() + " " + code);
    }

    public HuffmanNode pull() {
        HuffmanNode result = head;
        if (head != null) {
            size--;
            head = head.getNext();
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        HuffmanNode current = head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
