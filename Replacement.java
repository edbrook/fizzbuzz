import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Replacement<T,R> {
    private final Function<T, R> mapper;
    private final Predicate<T> predicate;

    public static <T,R> Replacement<T,R> of(R mapper, Predicate<T> predicate) {
        return new Replacement<T,R>(n -> mapper, predicate);
    }
    
    public static <T,R> Replacement<T,R> of(Supplier<R> mapper, Predicate<T> predicate) {
        return new Replacement<T,R>(n -> mapper.get(), predicate);
    }
    
    public static <T,R> Replacement<T,R> of(Function<T, R> mapper, Predicate<T> predicate) {
        return new Replacement<T,R>(mapper, predicate);
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