package com.mrathena.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mrathena.concurrent.tool.ThreadKit;

public class ExecutorServiceDemo {
	
	// 接口: ExecutorService
	// 方法: 

	public static void main(String[] args) {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();// 单线程
			
			// execute(Runnable) without return
			executor.execute(new Runnable() {
				public void run() {
					ThreadKit.sleep(1000);
					System.out.println("Asynchronous task");
				}
			});
			
			// submit(Runnable) with return null
			@SuppressWarnings("rawtypes")
			Future future = executor.submit(new Runnable() {
				@Override
				public void run() {
					ThreadKit.sleep(1000);
				}
			});
			System.out.println(future.get());
			
			// submit(Runnable, T的实例) with return T的实例
			Future<String> future2 = executor.submit(new Runnable() {
				@Override
				public void run() {
					ThreadKit.sleep(1000);
				}
			}, "success");
			System.out.println(future2.get());
			
			
			// submit(Callable<T>) with return Future<T>
			Future<String> future3 = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ThreadKit.sleep(1000);
					return "success";
				}
			});
			System.out.println(future3.get());
			
			// 任务组, 3个任务, 一个用时1000ms, 一个用时2000ms, 一个用时3000ms
			List<Callable<String>> tasks = new ArrayList<>();
			tasks.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ThreadKit.sleep(2000);
					return "Task1 success";
				}
			});
			tasks.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ThreadKit.sleep(1000);
					return "Task2 success";
				}
			});
			tasks.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ThreadKit.sleep(3000);
					return "Task3 success";
				}
			});
			
			// invokeAll(Collection<? extends Callable<T>>) with return List<Future<T>>
			executor = Executors.newSingleThreadExecutor();// 单线程, 任务串行
			executor = Executors.newFixedThreadPool(10);// 多线程, 任务并行, 执行时间短
			System.out.println("Tasks start");
			long start = System.currentTimeMillis();
			List<Future<String>> futures = executor.invokeAll(tasks);// 可以试试单/多两种ExecutorService的执行时间
			for (Future<String> item : futures) {
				System.out.println(item.get());
			}
			long end = System.currentTimeMillis();
			System.out.println("Tasks finished. Time " + (end - start) + "ms");
			
			// invokeAny(Collection<? extends Callable<T>>) with return T
			executor = Executors.newFixedThreadPool(10);// 多线程, 任务并行, 执行时间短
			System.out.println("Tasks start");
			long start2 = System.currentTimeMillis();
			String result = executor.invokeAny(tasks);// 可以试试单/多两种ExecutorService的执行时间
			long end2 = System.currentTimeMillis();
			System.out.println(result + ". Time " + (end2 - start2) + "ms");
			
			executor.shutdown();
			// ExecutorService 并不会立即关闭，但它将不再接受新的任务，而且一旦所有线程都完成了当前任务的时候，
			// ExecutorService 将会关闭。在 shutdown() 被调用之前所有提交给ExecutorService 的任务都被执行。
//			poll.shutdownNow();
			// Thread.sleep();会使正在执行中的任务的sleep失效(有返回值Future的除外)
			// 会立即尝试停止所有执行中的任务，并忽略掉那些已提交但尚未开始处理的任务。
			// 无法担保执行任务的正确执行。可能它们被停止了，也可能已经执行结束。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}