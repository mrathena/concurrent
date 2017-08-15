package com.mrathena.concurrent.basic;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUsage extends Thread {

	private static CyclicBarrier barrier = new CyclicBarrier(6, new Thread() {

		public void run() {
			System.out.println("start");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			System.out.println("finish");
		};
	});
	private final int sleepMilSecs;

	public CyclicBarrierUsage(int sleepMilSecs) {
		this.sleepMilSecs = sleepMilSecs;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(sleepMilSecs);
			System.out.println(sleepMilSecs + " secs slept");
			barrier.await();
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		new CyclicBarrierUsage(2000).start();
		new CyclicBarrierUsage(4000).start();
		new CyclicBarrierUsage(2100).start();
		new CyclicBarrierUsage(3000).start();
		new CyclicBarrierUsage(1000).start();
		new CyclicBarrierUsage(5000).start();
	}
}