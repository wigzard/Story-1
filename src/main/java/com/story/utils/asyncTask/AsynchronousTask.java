package com.story.utils.asyncTask;

/**
 * Created by alex on 29.05.16.
 */
public class AsynchronousTask {
    private Thread thread;
    private String threadName;
    private Runnable runnable;

    public AsynchronousTask(String name){
        this.threadName = name;
    }

    /**
     * Set the runnable instance
     * @param runnable the runnable
     */
    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public boolean isAlive() {
        return this.thread != null && this.thread.isAlive();

    }

    /**
     * Start thread with runnable from arguments
     * @param runnable the runnable
     */
    public void start(Runnable runnable){
        if (runnable == null){
            throw new NullPointerException("Runnable instance doesn't initialized");
        }

        if ( this.threadName.isEmpty()){
            throw new NullPointerException("Thread name doesn't initialized");
        }

        if ((this.thread != null) && (this.thread.getState() == Thread.State.TERMINATED)){
            this.thread.interrupt();
        }

        if ((this.thread == null) || (this.thread.isInterrupted())){
            this.thread = new Thread(runnable, this.threadName);
            this.thread.start();
        }
    }

    /**
     * Start thread with runnable from class
     */
    public void start(){
        this.start(this.runnable);
    }
}
