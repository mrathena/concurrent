package com.mrathena.concurrent.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
		new Thread(new Consumer(queue)).start();
		new Thread(new Producer(queue)).start();
	}
	
}

class Producer implements Runnable {

	private BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println("Producer produce 1");
			queue.put("1");
			Thread.sleep(1000);
			System.out.println("Producer produce 2");
			queue.put("2");
			Thread.sleep(1000);
			System.out.println("Producer produce 3");
			queue.put("3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Consumer implements Runnable {

	protected BlockingQueue<String> queue = null;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			System.out.println("Consumer consume " + queue.take());
			System.out.println("Consumer consume " + queue.take());
			System.out.println("Consumer consume " + queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}