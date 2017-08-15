package com.mrathena.concurrent;

import java.util.concurrent.CyclicBarrier;

import com.mrathena.concurrent.tool.ThreadKit;

public class Test {

	static CyclicBarrier c = new CyclicBarrier(2, new A());

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ThreadKit.sleep(1000);
					System.out.println(1);
					c.await();
				} catch (Exception e) {

				}
			}
		}).start();

		try {
			ThreadKit.sleep(2000);
			System.out.println(2);
			c.await();
		} catch (Exception e) {

		}
	}

	static class A implements Runnable {

		@Override
		public void run() {
			System.out.println(3);
		}

	}

}