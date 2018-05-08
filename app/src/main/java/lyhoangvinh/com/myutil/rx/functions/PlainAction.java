package lyhoangvinh.com.myutil.rx.functions;

import io.reactivex.functions.Action;

/**
 * Created by vinh on 7/26/17.
 * Like {@link Action} but without Exception
 */

public interface PlainAction extends Action {

    /**
     * Run the action
     */
    @Override
    void run();
}
