package com.mrathena.concurrent.basic;

public class Sleep extends Thread {

	@Override
	public void run() {
		System.out.println("start");
		synchronized (this) { // sleep() can use (or not) any synchronization resource
			try {
				/**
				 * Do you know: <br>
				 * 1. Why sleep() is designed as a static method comparing with
				 * wait?<br>
				 * 2. Why sleep() must have a timeout parameter?
				 */
				this.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace(); // notify won't throw exception
			}
		}
	}

	public static void main(String[] args) {
		Thread thread = new Sleep();
		thread.start();
		try {
			sleep(2000);
		} catch (InterruptedException e) {}
		synchronized (thread) {
			System.out.println("Has sleep() released the lock!");
			thread.notify();
		}

		// sleep()和wait()的区别
		// 1. 这两个方法来自不同的类，sleep方法属于Thread，wait方法属于Object。
		// 2. 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
		// 3. wait,notify和notifyAll只能在同步控制方法（synchronized）或者同步控制块里面使用，而sleep可以在任何地方使用。
		// 4. sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常。(这条是在搞笑呢吧,都需要捕获异常)

	}
}