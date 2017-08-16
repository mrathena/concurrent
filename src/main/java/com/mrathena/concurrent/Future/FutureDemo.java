package com.mrathena.concurrent.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mrathena.concurrent.tool.ThreadKit;

public class FutureDemo {

	public static void main(String[] args) {
		try {
			// 常规方式, 需要等3000ms, 期间不能做别的事
//			String result = doSomething();
//			System.out.println(result);

			// Future方式
			System.out.println("Do something2 start");
			Future<String> future = doSomething2();
			while (!future.isCancelled() && !future.isDone()) {
				System.out.println("Do otherthing ...");
				ThreadKit.sleep(1000);
//				future.cancel(true);
			}
			System.out.println("Task finished: " + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String doSomething() {
		System.out.println("Do something start");
		ThreadKit.sleep(3000);// 假设这个方法的执行需要3000ms
		System.out.println("Do something end. Time 3000ms");
		return "success";
	}

	public static Future<String> doSomething2() {
		ExecutorService poll = Executors.newSingleThreadExecutor();
		Future<String> future = poll.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				ThreadKit.sleep(3000);// 假设这个方法的执行需要3000ms
				return "success";
			}
		});
		poll.shutdown();
		return future;
	}

}