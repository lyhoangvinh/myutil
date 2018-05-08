package lyhoangvinh.com.myutil.realm;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by vinh on 9/18/17.
 * Mix version of android {@link LiveData} for {@link RealmResults}
 */

public class LiveRealmResults<T extends RealmModel> extends LiveData<LiveRealmResultPair<T>> {

    private RealmResults<T> mRealmResults;

    private final OrderedRealmCollectionChangeListener<RealmResults<T>> listener = (realmResults, changeSet) -> {
        this.updateValue(new LiveRealmResultPair<T>(realmResults, changeSet));
    };

    public LiveRealmResults(@NonNull RealmResults<T> realmResults) {
        updateValue(new LiveRealmResultPair<T>(realmResults, null));
    }

    public RealmResults<T> getData() {
        return mRealmResults;
    }

    @Override
    protected void onActive() {
        super.onActive();
        mRealmResults.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        mRealmResults.removeChangeListener(listener);
    }

    protected void updateValue(LiveRealmResultPair<T> value) {
        try {
            this.setValue(value);
        } catch (Exception e) {
            // if we can't set value (since current thread is a background thread), we must call postValue() instead
            // java.lang.IllegalStateException: Cannot invoke setValue on a background thread
            // or NullPointerException if we are testing (can't get current looper when assert is main thread)
            this.postValue(value);
        }
    }

    @Override
    protected void setValue(LiveRealmResultPair<T> value) {
        super.setValue(value);
        mRealmResults = value.getData();
    }

    @Override
    protected void postValue(LiveRealmResultPair<T> value) {
        super.postValue(value);
        mRealmResults = value.getData();
    }

    /**
     * Convert {@link RealmResults} to Live data {@link LiveRealmResults}
     * @param realmResults input realm results
     * @param <T> type of model
     * @return live data version of given realm results
     */
    public static <T extends RealmModel> LiveRealmResults<T> asLiveData(RealmResults<T> realmResults){
        return new LiveRealmResults<T>(realmResults);
    }
}