package org.firstinspires.ftc.team7316.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andrew on 9/15/16.
 */
public class Scheduler {

    public static final Scheduler instance = new Scheduler();

    private ArrayList<Loopable> tasks = new ArrayList<Loopable>();
    private HashMap<Loopable, Boolean> hasInitialized = new HashMap<>();

    public Scheduler () {}

    public void addTask(Loopable task) {
        tasks.add(task);
        hasInitialized.put(task, true);
    }

    public void loop() {
        int i = 0;
        while (i < tasks.size()) {
            Loopable thisTask = tasks.get(i);
            if (hasInitialized.get(thisTask)) {
                thisTask.init();
                hasInitialized.put(thisTask, false);
            }
            if (thisTask.shouldRemove()) {
                tasks.remove(i);
                thisTask.terminate();
                hasInitialized.remove(thisTask);
            } else {
                thisTask.loop();
                i++;
            }
        }
    }

}
