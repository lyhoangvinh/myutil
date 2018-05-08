package lyhoangvinh.com.myutil.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Huynh Binh PC on 7/15/2016.
 */
public class MyThreadFactory implements ThreadFactory {

    private int threadPriority = Thread.NORM_PRIORITY - 2;

    public MyThreadFactory() {
    }

    public MyThreadFactory(int priority) {
        if (priority != 0) {
            this.threadPriority = priority;
        } else {
            this.threadPriority = Thread.NORM_PRIORITY - 2;
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(threadPriority);
        return thread;
    }
}
