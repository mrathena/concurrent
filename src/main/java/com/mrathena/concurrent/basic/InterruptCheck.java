package com.mrathena.concurrent.basic;

public class InterruptCheck extends Thread {

	// 这是中断的一种使用方式，看起来就像是一个标志位，线程A设置这个标志位，线程B时不时地检查这个标志位。
	@Override
	public void run() {
		System.out.println("start");
		while (true) {
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
		System.out.println("while exit");
	}

	public static void main(String[] args) {
		try {
			Thread thread = new InterruptCheck();
			thread.start();
			Thread.sleep(2000);
			// thread会一直运行, 直到main线程执行了thread.interrupt();
			thread.interrupt();
		} catch (Exception e) {}
	}

}