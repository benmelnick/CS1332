public class Test {
    public static void main(String[] args) {
        AVL<Integer> tree = new AVL<>();
        tree.add(20);
        tree.add(15);
        tree.add(55);
        tree.add(4);
        tree.add(30);
        tree.add(75);
        tree.add(2);
        tree.add(8);
        tree.mysteryRec(tree.getRoot());
    }
}
