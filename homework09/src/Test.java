public class Test {
    public static void main(String[] args) {
        int[] table = PatternMatching.buildFailureTable("AAGAAATAGTAAG", new CharacterComparator());
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
    }
}
