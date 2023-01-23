public class HuffmanNode implements Comparable<HuffmanNode> {
    private Character character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    private HuffmanNode next;

    public HuffmanNode(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.left = left;
        this.right = right;
        frequency = left.frequency + right.frequency;
    }

    public boolean hasChildren(){
        return right!=null && left!=null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return Integer.compare(frequency, node.frequency);
    }
    @Override
    public String toString() {
        return character  +""+ frequency;
    }

    public Character getCharacter(){
        return character;
    }

    public HuffmanNode getNext() {
        return next;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setNext(HuffmanNode next) {
        this.next = next;
    }
}
