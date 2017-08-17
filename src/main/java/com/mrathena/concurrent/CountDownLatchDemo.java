package com.mrathena.concurrent;

import java.util.concurrent.CountDownLatch;

import com.mrathena.concurrent.tool.ThreadKit;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		
		int workerNumber = 4;
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch finish = new CountDownLatch(workerNumber);
		
		// work等待修车
		for (int i = 0; i < workerNumber; i++) {
			new Thread(new worker(start, finish, (i + 1) * 1000), (i + 1) + "").start();
		}
		
		Driver driver = new Driver(start, finish);
		driver.driver();
	}
}

class Driver {

	private CountDownLatch start;
	private CountDownLatch finish;
	
	public Driver(CountDownLatch start, CountDownLatch finish) {
		this.start = start;
		this.finish = finish;
	}

	public void driver() {
		try {
			System.out.println("车正在跑");
			System.out.println("车正在减速停车");
			System.out.println("车停下来了, 开始修车");
			start.countDown();// start只计算一条线程, worker本来都处于被阻塞状态, start一减, worker全部开始工作了
			finish.await();// 等待4个修车工全部完成
			System.out.println("车修好了, 准备出发");
			System.out.println("车正字加速");
			System.out.println("车正在跑");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class worker implements Runnable {

	private CountDownLatch start;
	private CountDownLatch finish;
	private int fixCarTime;

	public worker(CountDownLatch start, CountDownLatch finish, int fixCarTime) {
		this.start = start;
		this.finish = finish;
		this.fixCarTime = fixCarTime;
	}

	@Override
	public void run() {
		try {
			String name = "[" + ThreadKit.getName() + "] ";
			start.await();// 等待修车
			System.out.println(name + "开始修车");
			ThreadKit.sleep(fixCarTime);
			System.out.println(name + "完成修车. 修车用时:" + fixCarTime + "ms");
			finish.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}