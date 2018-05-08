package lyhoangvinh.com.myutil.rx.functions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by vinh on 7/31/17.
 * Like {@link Predicate but without exception}
 */

public interface PlainPredicate<T> extends Predicate<T> {

    /**
     * Test the given input value and return a boolean.
     * @param t the value
     * @return the boolean result
     */
    @Override
    boolean test(@NonNull T t);
}
