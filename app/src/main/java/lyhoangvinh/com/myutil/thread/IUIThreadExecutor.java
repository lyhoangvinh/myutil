/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package lyhoangvinh.com.myutil.thread;

/**
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
public interface IUIThreadExecutor {
    /**
     * Causes the {@link Runnable} to be added to the message queue of the Main UI Thread
     * of the application.
     *
     * @param runnable {@link Runnable} to be executed.
     */
    void runOnUIThread(Runnable runnable);
}
