package com.blogWeb.delayQueue;

import java.util.concurrent.DelayQueue;

//定义使用整个延迟队列的任务类
class DelayedTaskConsumer implements Runnable{  
	private DelayQueue<DelayedTask> queue;
	public DelayedTaskConsumer(DelayQueue<DelayedTask> queue){
		this.queue = queue; 
	}  
	public void run(){  
		try{  
			if(queue.size() == 0)  
				Thread.interrupted();  
			while(!Thread.interrupted())  
				queue.take().run();


		}catch(InterruptedException ex){
		}  
		//<do something when delayedTask is run successfully>  
	}  
} 