import java.util.Arrays;
import java.util.List;

public class FizzBuzz {

    private static final List<Replacement<Integer,String>> replacements = Arrays.asList(
        Replacement.of("Fizz", n -> n % 3 == 0),
        Replacement.of("Buzz", n -> n % 5 == 0)
    );

    public static void main(String[] args) {
        String fmt = "%3d";
        for (int i = 1; i <= 100; i++) {
            StringBuilder sb = new StringBuilder();
            for (Replacement<Integer,String> nr : replacements) {
                nr.replace(i).ifPresent(sb::append);
            }
            if (sb.length() == 0) sb.append(i);
            sb.insert(0, " - ").insert(0, String.format(fmt, i));
            System.out.println(sb);
        }
    }
}