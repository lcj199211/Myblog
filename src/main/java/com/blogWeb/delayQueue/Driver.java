package com.blogWeb.delayQueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试平台
class Driver {
    static ExecutorService exec;
    static DelayQueue<DelayedTask> queue;

    static {
        exec = Executors.newCachedThreadPool();
        queue = new DelayQueue<DelayedTask>();
    }

    public static void main(String[] args) {

        queue.put(new DelayedTask(new Random().nextInt(5000), "开始"));

        exec.execute(new DelayedTaskConsumer(queue));
    }
}  