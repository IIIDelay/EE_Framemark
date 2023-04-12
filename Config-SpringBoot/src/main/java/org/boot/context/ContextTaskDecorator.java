package org.boot.context;

import org.iiidev.common.context.LoginVal;
import org.iiidev.common.context.OauthContext;
import org.springframework.core.task.TaskDecorator;

/**
 * ContextTaskDecorator
 * TaskDecorator: 官方api的大致意思：这是一个执行回调方法的装饰器，主要应用于传递上下文，或者提供任务的监控/统计信息
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 15:15:00
 */
public class ContextTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        LoginVal loginVal = OauthContext.get();
        return () -> {
            try {
                // 子线程, 父的值 => 子
                OauthContext.set(loginVal);
                runnable.run();
            } finally {
                // 线程结束，清空这些信息，否则可能造成内存泄漏
                OauthContext.clear();
            }
        };
    }
}
