package com.story.game.handlers;

import com.story.core.actions.EventArgs;
import com.story.core.actions.IEventHandler;
import com.story.core.entities.Npc;
import com.story.game.components.npc.MoveTask;
import com.story.game.handlers.eventArgs.NpcMoveEventArgs;
import com.story.utils.asyncTask.AsynchronousTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 19.05.16.
 */
public class NpcMoveHandler implements IEventHandler{
    private static final String TaskName = "RefreshListOfTask";
    private AsynchronousTask asyncTask;
    private TaskHashMap<Integer, MoveTask> tasks;

    public NpcMoveHandler() {
        this.asyncTask = new AsynchronousTask(TaskName);
        this.tasks = new TaskHashMap<>();
    }

    /**
     * Optimize the tasks object
     * @param listOfNpc the npc list
     */
    private void refreshTasks(List<Npc> listOfNpc){
        synchronized (TaskHashMap.class){
            if (this.tasks == null){
                this.tasks = new TaskHashMap<>();
            }

            if (this.tasks.size() == listOfNpc.size()){
                return;
            }

            if (listOfNpc.size() == 0){
                this.tasks.clear();
                return;
            }

            TaskHashMap<Integer, MoveTask> newList = new TaskHashMap<>();

            for (Npc npc: listOfNpc) {
                if (this.tasks.containsKey(npc.getId())){
                    newList.put(npc.getId(), this.tasks.get(npc.getId()));
                }
                else {
                    newList.put(npc.getId(), new MoveTask(npc));
                }
            }

            this.tasks = newList;
        }
    }

    /**
     * Execute tasks
     */
    private void executeTasks(){
        for(Map.Entry<Integer, MoveTask> task : tasks.entrySet()) {
            task.getValue().execute();
        }
    }

    /**
     * Handle the move action for list of Npc
     * @param args the instance of NpcMoveEventArgs
     */
    @Override
    public void onHandle(EventArgs args) {
        if (!(args instanceof NpcMoveEventArgs)){
            return;
        }

        final NpcMoveEventArgs npcArguments = (NpcMoveEventArgs)args;
        if (npcArguments.listOfNpc == null){
            this.tasks.clear();
            throw new NullPointerException("npc from NpcMoveEventArgs doesn't correct");
        }

        if (this.tasks.size() != npcArguments.listOfNpc.size()){
            this.asyncTask.start(new Runnable() {
                @Override
                public void run() {
                    refreshTasks(npcArguments.listOfNpc);
                    executeTasks();
                }
            });
        }
        else {
            executeTasks();
        }
    }

    private class TaskHashMap<K, V> extends HashMap<K, V>{}
}
