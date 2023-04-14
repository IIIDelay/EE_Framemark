package org.iiidev.common.task;

public abstract class ChildTask {

    /**
     * 线程池大小
     */
    protected final int POOL_SIZE = 3;

    /**
     * 数据拆分大小
     */
    protected final int SPLIT_SIZE = 4;

    protected String taskName;

    /**
     * 接收jvm关闭信号，实现优雅停机
     */
    protected volatile boolean terminal = false;

    public ChildTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * doExecute 程序执行入口
     */
    public abstract void doExecute();

    /**
     * terminal 优雅停机
     */
    public void terminal() {
        // 关机
        terminal = true;
        System.out.println(taskName + " shut down");
    }
}