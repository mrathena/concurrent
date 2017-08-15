package com.mrathena.concurrent.basic;

public class Volatile {

	public static void main(String[] args) {
		final Volatile volObj = new Volatile();
		Thread t2 = new Thread() {

			@Override
			public void run() {
				while (true) {
					volObj.check();
				}
			}
		};
		t2.start();
		Thread t1 = new Thread() {

			@Override
			public void run() {
				while (true) {
					volObj.swap();
				}
			}
		};
		t1.start();
	}

	volatile boolean boolValue;// use volatile to print "WTF!"
	//	boolean boolValue;// use volatile to print "WTF!"

	public void check() {
		// 有可能左边刚取出来, 在取右边之前, 另一个线程就修改了boolValue的值, 致使这个等式成立
		// 这就需要volaile来保证被其他线程能读到该变量的实时值(可能会有主存,每个线程各自的工作内存拷贝等问题)
		// 不同线程对这个变量进行操作时具有可见性，修改与写入操作都会存入主存中，并通知其他cpu中该变量缓存行无效，保证了每次读取都是最新的值
		if (this.boolValue == !this.boolValue) {
			// 该判断条件并不是原子操作
			System.out.println("WTF!");
		}
	}

	public void swap() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.boolValue = !this.boolValue;
	}

}