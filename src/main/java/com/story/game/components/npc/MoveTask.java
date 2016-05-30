package com.story.game.components.npc;

import com.story.core.entities.Npc;
import com.story.core.entities.PersonEntity;
import com.story.utils.asyncTask.AsynchronousTask;

import java.util.Random;

/**
 * Created by alex on 30.05.16.
 */
public class MoveTask {
    private AsynchronousTask asyncTask;
    private Runnable runnable;
    private String taskName;
    private int delay;

    public MoveTask(Npc npc){
        if (npc == null){
            throw new NullPointerException("Npc is null");
        }

        int min = 2;
        int max = 5;
        this.delay = new Random().nextInt(max - min + 1) + min;
        this.delay *= 1000;

        this.taskName = npc.getName() + "_" + npc.getId();
        this.asyncTask = new AsynchronousTask(this.taskName);
        this.runnable = this.createRunnable(npc);
    }

    /**
     * Create the runnable
     * @param npc the npc
     * @return new instance of runnable
     */
    private Runnable createRunnable(final Npc npc){
        return new Runnable() {
                    @Override
                    public void run() {
                        if (npc == null){
                            return;
                        }

                        try {
                            Thread.sleep(delay);
                            npc.move(PersonEntity.Direction.UP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
    }

    /**
     * @return The task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Execute the async task
     */
    public void execute(){
        if (this.asyncTask.isAlive()){
            return;
        }

        this.asyncTask.start(this.runnable);
    }
}
