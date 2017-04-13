package com.multithreading2;

/*
 * 等待/唤醒机制
 * 
 * 涉及的方法：
 * 1.wait(): 让线程处于冻结状态,被wait的线程会被存储到线程池中。
 * 2.notify()： 唤醒线程池中的一个线程（任意）。
 * 3.notifyAll()： 唤醒线程池中的所有线程。
 * 
 * 这些方法都必须定义在同步中，因为这些方法是用于操作线程状态的方法。
 * 必须要明确到底操作的是哪个锁上的线程。
 * 
 * 
 * 为什么操作线程的方法wait， notify， notifyAll 定义在object类中呢。
 * 因为这些方法是监视器的方法。监视器其实就是锁🔐
 * 锁可以是任意的对象。任意的对象调用的方法一定定义在object类中。
 * 
 */
//资源
class Resource {
	String name;
	String sex;
	boolean flag;
}

//输入
class Input implements Runnable {
	Resource r;

	Input(Resource r) {
		this.r = r;
	}

	int x = 0;

	public void run() {
		while (true) {
			synchronized(r) {
				if (r.flag)
					try {
						r.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (x == 0) {
					r.name = "mike";
					r.sex = "nan";
				} else {
					r.name = "丽丽";
					r.sex = "女女女女女女";
				}
				r.flag = true;
				r.notify();
			}
			x = (x + 1) % 2;
		}
	}
}

//输出

class Output implements Runnable {
	Resource r;

	Output(Resource r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			synchronized(r) {
				if (!r.flag)
					try {
						r.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println(r.name + "...." + r.sex);
				r.flag = false;
				r.notify();
			}
		}
	}
}

public class ResourceDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create source
		Resource r = new Resource();
		// create task
		Input in = new Input(r);
		Output out = new Output(r);
		// create threads and execute path
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		// start threads
		t1.start();
		t2.start();

	}

}
