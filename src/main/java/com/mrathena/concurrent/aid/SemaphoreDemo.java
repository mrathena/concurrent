package com.mrathena.concurrent.aid;

import java.util.Random;
import java.util.concurrent.Semaphore;

import com.mrathena.concurrent.tool.ThreadKit;

public class SemaphoreDemo {

	// 计数信号量由一个指定数量的 "许可" 初始化。每调用一次 acquire()，一个许可会被调用线程取走。
	// 每调用一次 release()，一个许可会被返还给信号量。因此，在没有任何 release() 调用时，
	// 最多有 N 个线程能够通过 acquire() 方法，N 是该信号量初始化时的许可的指定数量。
	// 这些许可只是一个简单的计数器。这里没啥奇特的地方。
	// 信号量主要有两种用途：
	// 1. 保护一个重要(代码)部分防止一次超过 N 个线程进入。
	// 2. 在两个线程之间发送信号
	public static void main(String[] args) {
		Runnable run = new Runnable() {

			final Random rand = new Random();
			// 一共只有三个坑，没有释放的就等待
			final Semaphore available = new Semaphore(3);

			@Override
			public void run() {
				try {
					int time = this.rand.nextInt(10);
					String name = "[" + ThreadKit.getName() + "] ";
					this.available.acquire();
					System.out.println(name + "will work for " + time + "s");
					Thread.sleep(1000 * time);
					System.out.println(name + "work done");
					this.available.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		for (int i = 0; i < 10; i++) {
			new Thread(run, i + "").start();
		}
	}
}