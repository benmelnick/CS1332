public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(7);
        tree.add(5);
        tree.removeLeaves();
    }
}
