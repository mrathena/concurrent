package com.mrathena.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.mrathena.concurrent.tool.ThreadKit;

public class ScheduledExecutorServiceDemo {

	// 接口: ScheduledExecutorService
	// 方法: schedule(Runnable command, long delay, TimeUnit unit)
	// 方法: schedule(Callable<V> callable, long delay, TimeUnit unit)
	// 方法: scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
	// 方法: scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)

	// 父接口: Executor, ExecutorService
	// 实现类: ScheduledThreadPoolExecutor
	
	public static void main(String[] args) {
		try {
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			
			// schedule(Runnable, , ) with return null
			@SuppressWarnings("rawtypes")
			ScheduledFuture future =  executor.schedule(new Runnable() {
				@Override
				public void run() {
					ThreadKit.sleep(1000);
				}
			}, 5L, TimeUnit.SECONDS);// 5秒后执行该线程
			System.out.println(future.get());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}