package com.mrathena.concurrent.basic;

public class InterruptWait extends Thread {

	public static Object lock = new Object();

	@Override
	public void run() {
		// 在这种方式下，如果使用wait方法处于等待中的线程，被另一个线程使用中断唤醒，
		// 于是抛出InterruptedException，同时，中断标志清除，这时候我们通常会在捕获该异常的地方重新设置中断，
		// 以便后续的逻辑通过检查中断状态来了解该线程是如何结束的。
		System.out.println("start");
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().isInterrupted());
				Thread.currentThread().interrupt(); // set interrupt flag again
				System.out.println(Thread.currentThread().isInterrupted());
			}
		}
	}

	public static void main(String[] args) {
		try {
			Thread thread = new InterruptWait();
			thread.start();
			sleep(2000);
			thread.interrupt();
		} catch (Exception e) {}
	}
}