package com.siwuxie095.network.chapter7th.example3rd;

import java.util.Collections;
import java.util.List;

/**
 * 在事件循环中执行任务
 *
 * @author Jiajing Li
 * @date 2020-11-19 07:54:04
 */
@SuppressWarnings("all")
public class EventLoopExamples {

    /**
     * 在事件循环中执行任务
     */
    public static void executeTaskInEventLoop() {
        boolean terminated = true;
        // ...
        while (!terminated) {
            // 阻塞，直到有事件已经就绪可被运行
            List<Runnable> readyEvents = blockUntilEventsReady();
            for (Runnable ev : readyEvents) {
                // 循环遍历，并处理所有的事件
                ev.run();
            }
        }
    }

    private static final List<Runnable> blockUntilEventsReady() {
        return Collections.<Runnable>singletonList(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

