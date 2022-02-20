import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Replacement<T,R> {
    private final Function<T, R> mapper;
    private final Predicate<T> predicate;

    public static <T,R> Replacement<T,R> of(R constantValue, Predicate<T> predicate) {
        return new Replacement<>(n -> constantValue, predicate);
    }

    public static <T,R> Replacement<T,R> of(Supplier<R> valueSupplier, Predicate<T> predicate) {
        return new Replacement<>(n -> valueSupplier.get(), predicate);
    }

    public static <T,R> Replacement<T,R> of(Function<T, R> mapper, Predicate<T> predicate) {
        return new Replacement<>(mapper, predicate);
    }

    public Optional<R> replace(T n) {
        R out = null;
        if (predicate.test(n)) {
            out = mapper.apply(n);
        }
        return Optional.ofNullable(out);
    }

    private Replacement(Function<T, R> mapper, Predicate<T> predicate) {
        this.mapper = mapper;
        this.predicate = predicate;
    }
}
