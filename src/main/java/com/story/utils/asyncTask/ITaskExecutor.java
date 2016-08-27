package com.story.utils.asyncTask;

import java.util.concurrent.Callable;

/**
 * Created by alex on 26.08.16.
 * Represent abstraction of execution for the async task
 */
interface ITaskExecutor<T> extends ITaskExecutorFeature {
    /**
     * Represent signature for the method of execution async task
     * @param callable the callable instance
     */
    void execute(Callable<T> callable);

    /**
     * Represent signature for the method of execution async task
     * @param runnable the task
     * @param millisecondPeriod th period of execution
     */
    void execute(Runnable runnable, int millisecondPeriod);

    /**
     * Represent signature for the method of execution async task
     * @param runnable the runnable instance
     */
    void execute(Runnable runnable);

    /**
     * Represent signature of shut down method
     */
    void shutDown();
}
