package com.mrathena.concurrent.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomicity {

	private static volatile int nonAtomicCounter = 0;
	private static volatile AtomicInteger atomicCounter = new AtomicInteger(0);
	private static int times = 0;// 表示第times次发起多线程才出现++运算没有算够1000的情况

	public static void caculate() {
		times++;
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					nonAtomicCounter++;
					atomicCounter.incrementAndGet();
					System.out.println(nonAtomicCounter + " - " + atomicCounter.get());
				}
			}).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}

	public static void main(String[] args) {
		caculate();
		while (nonAtomicCounter == 1000) {
			// 如果++运算正好也算到了1000,那就重置,重新计算,确保出现没有算够1000的情况,times表示重新计算的次数
			nonAtomicCounter = 0;
			atomicCounter.set(0);
			caculate();
		}
		System.out.println("Non-atomic counter: " + times + " : " + nonAtomicCounter);
		System.out.println("Atomic counter: " + times + " : " + atomicCounter);
	}
}