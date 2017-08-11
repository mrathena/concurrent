package com.mrathena.concurrent.basic;

public class SuspendAndResume extends Thread {
	
	@Override
	public void run() {
		synchronized (this) {
			while (true)
				;
		}
	}

	public static void main(String[] args) {
		Thread thread = new SuspendAndResume();
		thread.start();

		try {
			sleep(1000);
		} catch (InterruptedException e) {}

		thread.suspend();

		synchronized (thread) { // dead lock, 这里的thread就是线程里面的this,两者指的是同一个对象
			System.out.println("got the lock");
			thread.resume();
		}
		
		// suspend:挂起
		// resume:恢复
		// 为什么要废弃stop()、suspend()和resume()?
		// 1.从上面的代码可以看出，Suspend线程被挂起时，依然占有锁，而当main线程期望去获取该线程来唤醒它时，彻底瘫痪了。
		// 由于suspend在这里是无期限限制的，这会变成一个彻彻底底的死锁。
	}
}