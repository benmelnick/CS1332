import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(70);
        list.add(20);
        list.add(10);
        list.add(45);
        list.add(30);
        list.add(15);
        list.add(5);
        list.add(25);
        MinHeap<Integer> heap = new MinHeap<>(list);
        for (int i = 0; i < heap.getBackingArray().length; i++) {
            System.out.print(heap.getBackingArray()[i] + " ");
        }
    }
}
