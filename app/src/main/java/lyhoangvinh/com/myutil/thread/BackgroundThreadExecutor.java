/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package lyhoangvinh.com.myutil.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Decorated {@link ThreadPoolExecutor} Singleton class based on
 * 'Initialization on Demand Holder' pattern.
 */
public class BackgroundThreadExecutor implements IBackgroundThreadExecutor {

    private static final int INITIAL_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 10;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> workQueue;
    private final ThreadPoolExecutor threadPoolExecutor;

    private BackgroundThreadExecutor() {
        this.workQueue = new LinkedBlockingQueue<Runnable>();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, this.workQueue, new MyThreadFactory(Thread.NORM_PRIORITY - 2));

    }

    public static BackgroundThreadExecutor getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * {@inheritDoc}
     *
     * @param runnable The class that implements {@link Runnable} interface.
     */
    @Override
    public void runOnBackground(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.threadPoolExecutor.execute(runnable);
    }

    private static class LazyHolder {
        private static final BackgroundThreadExecutor INSTANCE = new BackgroundThreadExecutor();
    }
}