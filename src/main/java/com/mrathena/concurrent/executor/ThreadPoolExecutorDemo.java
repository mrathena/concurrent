package com.mrathena.concurrent.executor;

public class ThreadPoolExecutorDemo {
    
/*
【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样
的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
说明：Executors 返回的线程池对象的弊端如下：
1）FixedThreadPool 和 SingleThreadPool:
允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
2）CachedThreadPool 和 ScheduledThreadPool:
允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
*/

	// 强烈建议程序员使用较为方便的 Executors 工厂方法 
	// Executors.newCachedThreadPool()（无界线程池，可以进行自动线程回收）、
	// Executors.newFixedThreadPool(int)（固定大小线程池）和 
	// Executors.newSingleThreadExecutor()（单个后台线程），它们均为大多数使用场景预定义了设置。
	
}