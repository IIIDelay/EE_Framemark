package org.iiidev.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TaskProcessUtil
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 14:25:00
 */
public class TaskProcessUtil {
    /**
     * 每个任务，都有自己单独的线程池
     */
    private static Map<String, ExecutorService> executorMap = new ConcurrentHashMap<>();

    /**
     * init 初始化一个线程池
     *
     * @param poolName poolName
     * @param poolSize poolSize
     * @return ExecutorService
     */
    private static ExecutorService init(String poolName, int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("Pool-" + poolName).setDaemon(false).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }


    /**
     * getOrInitExecutors 获取线程池
     *
     * @param poolName poolName
     * @param poolSize poolSize
     * @return ExecutorService
     */
    public static ExecutorService getOrInitExecutors(String poolName, int poolSize) {
        ExecutorService executorService = executorMap.get(poolName);
        if (null == executorService) {
            synchronized (TaskProcessUtil.class) {
                executorService = executorMap.get(poolName);
                if (null == executorService) {
                    executorService = init(poolName, poolSize);
                    executorMap.put(poolName, executorService);
                }
            }
        }
        return executorService;
    }

    /**
     * releaseExecutors 回收线程资源
     *
     * @param poolName poolName
     */
    public static void releaseExecutors(String poolName) {
        Optional.ofNullable(executorMap.remove(poolName)).ifPresent(ExecutorService::shutdown);
    }
}
