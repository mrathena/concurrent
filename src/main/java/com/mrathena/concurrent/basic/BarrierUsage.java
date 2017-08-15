package com.mrathena.concurrent.basic;

import java.util.concurrent.CyclicBarrier;

public class BarrierUsage extends Thread {

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

	public BarrierUsage(int sleepMilSecs) {
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
		new BarrierUsage(2000).start();
		new BarrierUsage(4000).start();
		new BarrierUsage(2100).start();
		new BarrierUsage(3000).start();
		new BarrierUsage(1000).start();
		new BarrierUsage(5000).start();
	}
}