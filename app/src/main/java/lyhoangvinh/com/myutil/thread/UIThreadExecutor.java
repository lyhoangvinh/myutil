/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package lyhoangvinh.com.myutil.thread;

import android.os.Handler;
import android.os.Looper;

/**
 * MainThread (UI Thread) implementation based on a Handler instantiated with the main
 * application Looper.
 */
public class UIThreadExecutor implements IUIThreadExecutor {

    private final Handler handler;

    private UIThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    public static UIThreadExecutor getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Causes the Runnable r to be added to the message queue.
     * The runnable will be run on the main thread.
     *
     * @param runnable {@link Runnable} to be executed.
     */
    @Override
    public void runOnUIThread(Runnable runnable) {
        handler.post(runnable);
    }

    private static class LazyHolder {
        private static final UIThreadExecutor INSTANCE = new UIThreadExecutor();
    }
}
