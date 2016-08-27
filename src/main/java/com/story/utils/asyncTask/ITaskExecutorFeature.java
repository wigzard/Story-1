package com.story.utils.asyncTask;

import java.util.concurrent.Future;

/**
 * Created by alex on 25.08.16.
 * Represent signature of the Future object for the Callable
 */
public interface ITaskExecutorFeature {
    /**
     * @return instance of future, which should be returned
     */
    Future getFuture();
}
