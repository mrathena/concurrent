package com.mrathena.concurrent.basic;

public class Lock {

	private static Object o = new Object();
	static Lock lock = new Lock();

	// 这个例子可以反映对一个锁竞争的现象，结合上面的例子，理解下面这两条，就可以很容易理解synchronized关键字的使用：
	// a.动态方法使用synchronized修饰，相当于synchronized(this)。会互相卡住
	// b.静态方法使用synchronized修饰，相当于synchronized(Lock.class)。会互相卡住
	// c.objectBlock
	// a,b,c, 3者互相卡不住
	
	// lock on dynamic method
	public synchronized void dynamicMethod() {
		System.out.println("dynamic method start");
		Lock.sleepSilently(2000);
		System.out.println("dynamic method end");
	}
	// lock on static method
	public static synchronized void staticMethod() {
		System.out.println("static method start");
		Lock.sleepSilently(2000);
		System.out.println("static method end");
	}
	// lock on this
	public void thisBlock() {
		synchronized (this) {
			System.out.println("this block start");
			Lock.sleepSilently(2000);
			System.out.println("this block end");
		}
	}
	// lock on the class
	public static void classBlock() {
		synchronized (Lock.class) {
			System.out.println("static block start");
			Lock.sleepSilently(2000);
			System.out.println("static block end");
		}
	}
	// lock on an object
	public void objectBlock() {
		synchronized (Lock.o) {
			System.out.println("dynamic block start");
			Lock.sleepSilently(2000);
			System.out.println("dynamic block end");
		}
	}

	private static void sleepSilently(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// object lock test
		new Thread() {
			@Override
			public void run() {
				Lock.lock.dynamicMethod();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				Lock.lock.thisBlock();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				Lock.lock.objectBlock();
			}
		}.start();

		Lock.sleepSilently(3000);
		System.out.println();

		// class lock test
		new Thread() {
			@Override
			public void run() {
				Lock.staticMethod();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				Lock.classBlock();
			}
		}.start();
		
		// 以下是我自己填写的内容
//		new Thread() {
//			@Override
//			public void run() {
//				Lock.lock.thisBlock();
//			}
//		}.start();
//		new Thread() {
//			@Override
//			public void run() {
//				Lock.lock.dynamicMethod();
//			}
//		}.start();
//		new Thread() {
//			@Override
//			public void run() {
//				Lock.classBlock();
//			}
//		}.start();
//		new Thread() {
//			@Override
//			public void run() {
//				Lock.staticMethod();
//			}
//		}.start();
//		new Thread() {
//			@Override
//			public void run() {
//				Lock.lock.objectBlock();
//			}
//		}.start();

	}
}