package com.story.utils.asyncTask;

import com.story.system.IDisposable;

import java.util.concurrent.*;

/**
 * Created by alex on 25.08.16.
 * Represent the executor of async task. Task executing one or more time.
 */
public class AsyncTaskExecutor implements ITaskExecutor, IDisposable {
    /**
     * The service executor.
     */
    private ScheduledExecutorService executor;

    /**
     * The result of execute an async task
     */
    private Future futureResult;

    /**
     * Initialize new instance of {@link AsyncTaskExecutor}
     */
    AsyncTaskExecutor(){
        this.executor = Executors.newScheduledThreadPool(1);
    }

    /**
     * Execute async task with callable
     * @param callable the callable instance
     */
    @Override
    public void execute(Callable callable) {
        if (this.executor == null){
            throw new NullPointerException("The async task doesn't created");
        }

        this.futureResult = this.executor.submit(callable);
    }

    /**
     * Execute the task with delay
     * @param runnable the task
     * @param millisecondPeriod th period of execution
     */
    @Override
    public void execute(Runnable runnable, int millisecondPeriod) {
        if (this.executor == null){
            throw new NullPointerException("The async task doesn't created");
        }

        this.executor.scheduleWithFixedDelay(runnable, 0, millisecondPeriod, TimeUnit.MILLISECONDS);
    }

    /**
     * Execute async task with runnable
     * @param runnable the runnable instance
     */
    @Override
    public void execute(Runnable runnable) {
        if (this.executor == null){
            throw new NullPointerException("The async task doesn't created");
        }

        this.futureResult = this.executor.submit(runnable);
    }

    /**
     * Shutting down the executor
     */
    @Override
    public void shutDown(){
        if ((this.executor != null) && (!this.executor.isShutdown())){
            this.executor.shutdown();
        }
    }

    /**
     * Gets the feature result from executor
     * @return the instance of feature
     */
    @Override
    public Future getFuture() {
        return this.futureResult;
    }

    @Override
    public void dispose() {
        if ((this.executor != null) && (!this.executor.isShutdown())){
            this.executor.shutdown();
        }

        this.futureResult = null;
        this.executor = null;
    }
}
