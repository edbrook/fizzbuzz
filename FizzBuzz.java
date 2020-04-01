import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FizzBuzz {

    private static final Predicate<Integer> mooPredicate = n -> System.nanoTime() % 100 < 50;
    private static final Predicate<Integer> dateTimeNowPredicate = n -> System.nanoTime() % 100 > 75;
    private static final Predicate<Integer> fiftyFiftyPredicate = n -> Math.random() > 0.5;

    private static final Supplier<String> dateTimeNow = () -> "[" + LocalDateTime.now() + "]";

    private static final Function<Integer, String> multiplyByTwo = n -> "{" + (n * 2) + "}";

    private static final List<Replacement<Integer,String>> replacements = Arrays.asList(
        Replacement.of("Fizz", n -> n % 3 == 0),
        Replacement.of("Buzz", n -> n % 5 == 0),
        Replacement.of("Mooo", mooPredicate),
        Replacement.of(dateTimeNow, dateTimeNowPredicate),
        Replacement.of(multiplyByTwo, fiftyFiftyPredicate)
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