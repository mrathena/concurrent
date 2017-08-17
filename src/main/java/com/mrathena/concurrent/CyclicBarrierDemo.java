package com.mrathena.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo extends Thread {

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

	public CyclicBarrierDemo(int sleepMilSecs) {
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
		new CyclicBarrierDemo(2000).start();
		new CyclicBarrierDemo(4000).start();
		new CyclicBarrierDemo(2100).start();
		new CyclicBarrierDemo(3000).start();
		new CyclicBarrierDemo(1000).start();
		new CyclicBarrierDemo(5000).start();
	}
}