package com.mrathena.concurrent;

import java.util.concurrent.Exchanger;

import com.mrathena.concurrent.tool.ThreadKit;

public class ExchangerDemo {

	public static void main(String[] args) {
		Exchanger<Object> exchanger = new Exchanger<>();
		ExchangerRunnable run1 = new ExchangerRunnable(exchanger, "A");
		ExchangerRunnable2 run2 = new ExchangerRunnable2(exchanger, "B");
		new Thread(run1, "1").start();
		ThreadKit.sleep(2000);
		new Thread(run2, "2").start();
	}
	// 从官方的javadoc可以知道，当一个线程到达exchange调用点时，如果它的伙伴线程此前已经调用了此方法，
	// 那么它的伙伴会被调度唤醒并与之进行对象交换，然后各自返回。如果它的伙伴还没到达交换点，那么当前线程将会被挂起，
	// 直至伙伴线程到达——完成交换正常返回；或者当前线程被中断——抛出中断异常；又或者是等候超时——抛出超时异常。
	// 注意: exchanger.exchange()的返回值才是交换后的值
	// 举例: 生产者/消费者模式, 同时开启生产/消费两个线程, 首先消费线程执行exchange而等待生产, 待生产线程产出足够量的数据就执行exchange, 
	//      这样两个线程都到达了同步点, 消费线程拿到数据去跑数据, 生产线程不接收消费线程交换的数据而直接清空容器再去生产数据(生产把数据给消费), 
	//      两个线程将在下一个同步点再次交换数据
	//      两个线程的run()内:  while (!Thread.interrupted() && !isDone) {...其他内容都在这里面...}
	//      isDone的声明: private static volatile boolean isDone = false;
}

class ExchangerRunnable implements Runnable {

	private Exchanger<Object> exchanger;
	private Object object;

	public ExchangerRunnable(Exchanger<Object> exchanger, Object object) {
		this.exchanger = exchanger;
		this.object = object;
	}

	@Override
	public void run() {
		try {
			String name = "[" + ThreadKit.getName() + "] ";
			System.out.println(name + "Before exchange : " + this.object);
			this.object = this.exchanger.exchange(this.object);
			System.out.println(name + "After exchange : " + this.object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ExchangerRunnable2 implements Runnable {

	private Exchanger<Object> exchanger;
	private Object object;

	public ExchangerRunnable2(Exchanger<Object> exchanger, Object object) {
		this.exchanger = exchanger;
		this.object = object;
	}

	@Override
	public void run() {
		try {
			String name = "[" + ThreadKit.getName() + "] ";
			System.out.println(name + "Before exchange : " + this.object);
			this.object = this.exchanger.exchange(this.object);
			System.out.println(name + "After exchange : " + this.object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}