package lyhoangvinh.com.myutil.rx;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;
import lyhoangvinh.com.myutil.rx.functions.PlainAction;
import lyhoangvinh.com.myutil.rx.functions.PlainConsumer;


/**
 * Created by vinh on 10/10/2017.
 *
 */

public abstract class RxLoader<Source, Result> {

    private final Source mSource;

    public RxLoader(Source source) {
        this.mSource = source;
    }

    @Nullable
    protected abstract Result load(Source source);

    public void load(@NonNull PlainConsumer<Result> consumer) {
        load(consumer, null);
    }

    public void load(@NonNull PlainConsumer<Result> consumer, @Nullable PlainAction onError) {
        Single<Result> single = Single.create(e -> {
            Result result = load(mSource);
            if (result != null) {
                e.onSuccess(result);
            } else {
                e.onError(new NullPointerException("Got null result"));
            }
        });
        single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(consumer, throwable -> {
                    if (onError != null) {
                        onError.run();
                    }
                });
    }
}
