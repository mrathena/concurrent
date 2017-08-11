package com.mrathena.concurrent.basic;

public class Stop extends Thread {

	@Override
	public void run() {
		try {
			while (true)
				;
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Thread thread = new Stop();
		thread.start();

		try {
			sleep(1000);
		} catch (InterruptedException e) {}

		thread.stop(new Exception("stop")); // note the stack trace
		
		// 为什么要废弃stop()、suspend()和resume()?
		// 1.使用stop来终止一个线程是不讲道理、极其残暴的，不论目标线程在执行任何语句，
		// 一律强行终止线程，最终将导致一些残缺的对象和不可预期的问题产生。
		// 2.被终止的线程没有任何异常抛出，你在线程终止后找不到任何被终止时执行的代码行，
		// 或者是堆栈信息（上面代码打印的异常仅仅是main线程执行stop语句的异常而已，并非被终止的线程）。
	}
}