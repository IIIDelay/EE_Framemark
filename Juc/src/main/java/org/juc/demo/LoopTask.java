package org.juc.demo;

import org.apache.commons.collections4.CollectionUtils;
import org.iiidev.common.task.ChildTask;
import org.juc.task.ChildTaskImpl;

import java.util.ArrayList;
import java.util.List;

public class LoopTask {
    private List<ChildTask> childTasks;

    public void initLoopTask() {
        childTasks = new ArrayList();
        childTasks.add(new ChildTaskImpl("childTask1"));
        childTasks.add(new ChildTaskImpl("childTask2"));
        for (final ChildTask childTask : childTasks) {
            new Thread(childTask::doExecute).start();
        }
    }

    public void shutdownLoopTask() {
        if (CollectionUtils.isNotEmpty(childTasks)) {
            childTasks.forEach(ChildTask::terminal);
        }
    }

    public static void main(String args[]) throws Exception {
        LoopTask loopTask = new LoopTask();
        loopTask.initLoopTask();
        loopTask.shutdownLoopTask();
    }
}