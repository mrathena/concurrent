package com.mrathena.concurrent.basic;

public class ThreadLocalUsage extends Thread {

	public User user = new User();

	public User getUser() {
		return this.user;
	}

	@Override
	public void run() {
		this.user.set("var1");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println(this.user.get());
		}
	}

	public static void main(String[] args) {
		ThreadLocalUsage thread = new ThreadLocalUsage();
		thread.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {}
		thread.user.set("var2");
		System.out.println("---" + thread.getUser().get());
		// 上面的例子会一直打印var1，而不会打印var2，就是因为不同线程中的ThreadLocal是互相独立的。?????????????
	}
}

class User {

	// is it must be static?
	// 静态变量: 该类所有实例共享该静态变量, 只有一个实例的时候和动态没有区别, 多个实例的时候就区别表现出来了
	private static ThreadLocal<Object> enclosure = new ThreadLocal<>();

	public void set(Object object) {
		User.enclosure.set(object);
	}

	public Object get() {
		return User.enclosure.get();
	}
}