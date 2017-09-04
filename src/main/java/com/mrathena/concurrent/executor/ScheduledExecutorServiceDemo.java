package com.mrathena.concurrent.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

			// schedule(Runnable, , ), 返回值只能是null, 可以不用管
//			executor.schedule(new Runnable() {
//				@Override
//				public void run() {
//					ThreadKit.sleep(1000);
//				}
//			}, 5L, TimeUnit.SECONDS);// 5秒后执行该线程

			// schedule(Callable<V>, , ) 
//			ScheduledFuture<String> future2 = executor.schedule(new Callable<String>() {
//				@Override
//				public String call() throws Exception {
//					ThreadKit.sleep(1000);
//					return "success";
//				}
//			}, 5L, TimeUnit.SECONDS);
//			System.out.println(future2.get());
			
			// scheduleAtFixedRate(Runnable, , , )
			// 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
			// 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行(不管上一次有没有执行完)，
			// 接着在 initialDelay + 2 * period 后执行(不管上一次有没有执行完)，依此类推。
			executor.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("A: " + sdf.format(new Date()));
				}
			}, 2L, 1L, TimeUnit.SECONDS);
			
			// scheduleWithFixedDelay(Runnable, , , )
			// 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
			// 也就是将在 initialDelay 后开始执行，然后在上一次执行完后 再过delay 后执行，
			// 接着在上一次执行完后 再过delay 后执行，依此类推。
			executor.scheduleWithFixedDelay(new Runnable() {
				@Override
				public void run() {
					ThreadKit.sleep(1100);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("B: " + sdf.format(new Date()));
				}
			}, 2L, 1L, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}