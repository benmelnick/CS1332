import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class Test {
    public static void fizzBuzz(int n, HashMap<Integer, String> vals) {
        Set<Integer> nums = vals.keySet();
        StringBuilder output = new StringBuilder();
        for (Integer i : nums) {
            if (n % i == 0) {
                output.append(vals.get(i));
            }
        }
        if (output.length() == 0) {
            output.append(n);
        }
        System.out.println(output);
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        for (int i = 1; i <= 100; i++) {
            fizzBuzz(i, map);
        }
    }

    public static void fizzNormal(int n) {
        StringBuilder output = new StringBuilder("");
        if (n % 3 == 0) {
            output.append("Fizz");
        }
        if (n % 5 == 0) {
            output.append("Buzz");
        }
        System.out.println(output.length() > 0 ? output : n);
    }
}
