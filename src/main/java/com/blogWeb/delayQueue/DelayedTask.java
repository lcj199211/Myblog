package com.blogWeb.delayQueue;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

//定义延迟队列中的任务元素，可以通过extends实现不同的run方法；
class DelayedTask implements Runnable, Delayed {
    private final long trigger;    //触发信号：任务线程在加入队列，延迟期满，即将要执行时的系统时间；
    private String msg;

    public DelayedTask(long delta, String msg) {
        System.out.println(msg + "=DelayedTask=" + delta);
        trigger = System.currentTimeMillis() + delta;
        this.msg = msg;
    }

    public long getDelay(TimeUnit unit) {   //提供TimeUnit的时间单位的转换；
        return unit.convert(trigger - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }

    public void run() {
        //todo
        System.out.println("==执行业务==" + msg);
        System.out.println("==重置时间==" + msg);



        //
        Date date = RandomDate();
        long time = date.getTime() - System.currentTimeMillis();
        Driver.queue.put(new DelayedTask(5000, UUID.randomUUID().toString()));
    }

    /**
     * 按照规则生成下一个任务时间点
     * @return
     */
    private Date RandomDate() {
        return new Date();
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), o.getDelay(TimeUnit.NANOSECONDS));
    }
} 