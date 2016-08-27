package com.story.utils.asyncTask;

import com.story.utils.GlobalHelper;
import com.story.utils.log.Trace;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by alex on 26.08.16.
 * Represent scheduler of async tasks
 */
public class TaskScheduler {
    /**
     * The instance of {@link TaskScheduler}
     */
    private static TaskScheduler instance;

    /**
     * The map of executors
     */
    private HashMap<String, AsyncTaskExecutor> executorMap;

    /**
     * Initialize new instance of {@link TaskScheduler}
     */
    private TaskScheduler(){
    }

    /**
     * Running the single task
     * @param task the task which should be executed
     */
    private void runSingleTask(Runnable task){
        AsyncTaskExecutor executor = new AsyncTaskExecutor();
        executor.execute(task);
        executor.shutDown();
    }

    /**
     * Run the task by timeout
     * @param taskName the name of task
     * @param task the task
     * @param millisecondPeriod the period of running
     */
    private void runCycleTask(String taskName, Runnable task, int millisecondPeriod){
        if (this.executorMap == null){
            this.executorMap = new HashMap<>();
        }

        if (GlobalHelper.isNullOrEmpty(taskName)){
            throw new NullPointerException("The task name shouldn't be null");
        }

        AsyncTaskExecutor executor = new AsyncTaskExecutor();
        executor.execute(task, millisecondPeriod);

        this.executorMap.put(taskName, executor);
    }

    /**
     * Run the task with result
     * @param task the task
     */
    private void runCallableTask(Callable task){
        AsyncTaskExecutor executor = new AsyncTaskExecutor();
        executor.execute(task);
        executor.shutDown();
    }

    /**
     * Return result from future
     * @param taskName the name of task
     */
    private Object result(String taskName) throws ExecutionException, InterruptedException {
        if (this.executorMap == null){
            return null;
        }

        if (this.executorMap.containsKey(taskName)){
            return this.executorMap.get(taskName).getFuture().get();
        }

        return null;
    }

    /**
     * Terminate the thread
     * @param taskName the name of task
     */
    private void shutDown(String taskName){
        if (this.executorMap == null){
            this.executorMap = new HashMap<>();
        }

        if (this.executorMap.containsKey(taskName)){
            this.executorMap.get(taskName).shutDown();
        }
    }

    /**
     * Gets instance of {@link TaskScheduler}
     */
    private static TaskScheduler getInstance(){
        if (instance == null){
            instance = new TaskScheduler();
        }

        return instance;
    }

    /**
     * Execute the task which should be start once of time
     * @param runnable the task
     */
    public static void scheduleSingleTask(Runnable runnable){
        try {
            getInstance().runSingleTask(runnable);
        }
        catch (Exception e){
            Trace.error(e);
        }
    }

    /**
     * Run task by cycle
     * @param taskName the name of task
     * @param task the task
     * @param millisecondPeriod the delay between task running
     */
    public static void scheduleCycleTask(String taskName, Runnable task, int millisecondPeriod){
        try {
            getInstance().runCycleTask(taskName, task, millisecondPeriod);
        }
        catch (Exception e){
            Trace.error(e);
        }
    }

    /**
     * Run task with result
     * @param callable the function of execute
     */
    public static void scheduleTask(Callable callable){
        try {
            getInstance().runCallableTask(callable);
        }
        catch (Exception e){
            Trace.error(e);
        }
    }

    /**
     * Terminate thread
     * @param taskName the name of task
     */
    public static void terminate(String taskName){
        try {
            getInstance().shutDown(taskName);
        }
        catch (Exception e){
            Trace.error(e);
        }
    }

    /**
     * Gets result of execution
     * @param taskName the name of task
     * @return the result as result
     */
    public static Object getResult(String taskName){
        try{
            return getInstance().result(taskName);
        }
        catch (Exception e){
            Trace.error(e);
            return null;
        }
    }
}
