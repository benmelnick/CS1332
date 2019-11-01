public class Test {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(51, "C++");
        map.put(28, "Java");
        map.put(22, "Javascript");
        map.put(39, "Haskell");
        map.remove(51);
        map.put(80, "Python");
        System.out.println();
    }
}
