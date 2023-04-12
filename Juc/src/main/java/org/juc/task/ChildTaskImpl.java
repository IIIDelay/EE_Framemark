package org.juc.task;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.iiidev.common.task.ChildTask;
import org.iiidev.utils.TaskProcessUtil;
import org.juc.entity.User;
import org.juc.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * ChildTaskImpl
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 14:40:00
 */
public class ChildTaskImpl extends ChildTask {
    public ChildTaskImpl(String taskName) {
        super(taskName);
    }

    @Override
    public void doExecute() {
        int i = 0;
        while (true) {
            System.out.println(taskName + ":Cycle-" + i + "-Begin");
            // 获取数据
            List<User> datas = queryData();
            // 处理数据
            taskExecute(datas);
            System.out.println(taskName + ":Cycle-" + i + "-End");
            if (terminal) {
                // 只有应用关闭，才会走到这里，用于实现优雅的下线
                break;
            }
            i++;
        }
        // 回收线程池资源
        TaskProcessUtil.releaseExecutors(taskName);
    }

    // 处理数据
    private void doProcessData(List<User> datas, CountDownLatch latch) {
        try {
            for (User cat : datas) {
                System.out.println(taskName + ":" + cat + ",ThreadName:" + Thread.currentThread().getName());
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    // 处理单个任务数据
    private void taskExecute(List<User> sourceDatas) {
        if (CollectionUtils.isEmpty(sourceDatas)) {
            return;
        }
        // 将数据拆成4份
        List<List<User>> splitDatas = Lists.partition(sourceDatas, SPLIT_SIZE);
        final CountDownLatch latch = new CountDownLatch(splitDatas.size());

        // 并发处理拆分的数据，共用一个线程池
        for (final List<User> datas : splitDatas) {
            ExecutorService executorService = TaskProcessUtil.getOrInitExecutors(taskName, POOL_SIZE);
            executorService.submit(() -> doProcessData(datas, latch));
        }

        try {
            latch.await();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    // 获取永动任务数据
    private List<User> queryData() {
        List<User> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserService userService = new UserService();
            datas.add(userService.setUser("IIIDev" + i));
        }
        return datas;
    }
}
