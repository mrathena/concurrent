package com.mrathena.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Test {

	public static void main(String[] args) {
		try {
			int count = 10000;// 创建的子线程数
			CountDownLatch latch = new CountDownLatch(count);
			Map<String, String> map = new HashMap<>();
//			Map<String, String> map = new ConcurrentHashMap<>();
			for (int i = 0; i < count; i++) {
				String key = i + "";
				String threadName = "[" + i + "]";
				new Thread(new Runnable() {
					@Override
					public void run() {
						map.put(key, threadName);
						latch.countDown();
					}
				}, threadName).start();
			}
			latch.await();// 主线程等待
			System.out.println(map.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}