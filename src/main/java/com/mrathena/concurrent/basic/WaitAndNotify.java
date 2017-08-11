package com.mrathena.concurrent.basic;

public class WaitAndNotify extends Thread {

	@Override
	public void run() {
		System.out.println("start");
		synchronized (this) { // wait/notify/notifyAll use the same
								// synchronization resource
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace(); // notify won't throw exception
			}
		}
	}

	public static void main(String[] args) {
		Thread thread = new WaitAndNotify();
		thread.start();
		try {
			sleep(2000);
		} catch (InterruptedException e) {}
		synchronized (thread) {
			System.out.println("Wait() will release the lock!");
			thread.notify();
		}
		
		// 在wait和notify搭配使用的过程中，注意需要把它们锁定到同一个资源上（例如对象a），即：
		// 一个线程中synchronized(a)，并在同步块中执行a.wait()
		// 另一个线程中synchronized(a)，并在同步块中执行a.notify()
	}
}