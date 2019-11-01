import java.util.ArrayList;
import java.util.Random;

public class Test {
    private static String calc(String input) {
        //Person 2 put your implementation here
        ArrayList indices = new ArrayList<>();
        String shuffled = "";
        Random rand = new Random();
        for (int i = 0; i < input.length(); i++) {
            int index = rand.nextInt(input.length());
            while (indices.contains(index)) {
                index = rand.nextInt(input.length());
            }
            indices.add(index);
            shuffled += Character.toString(input.charAt(index));
        }
        return shuffled;
    }


    public static void main(String[] args) {

        System.out.println(calc("hello"));

    }
}
