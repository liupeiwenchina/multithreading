/*
 * 地址： https://www.youtube.com/watch?v=2O6FAmSrHOg&index=18&t=1057s&list=PLaYqF7AnyNPcQtSMS5vrRJkZnwssukKwz
 * 线程间通信：
 * 多个线程处理统一资源，但是任务不同
 * 
 */
package com.multithreading;



//资源
class Resource {
	String name;
	String sex;
}

// 输入
class Input implements Runnable {
	Resource r;

	Input(Resource r) {
		this.r = r;
	}

	int x = 0;

	public void run() {
		while (true) {
			synchronized(r) {
				if (x == 0) {
					r.name = "mike";
					r.sex = "nan";
				} else {
					r.name = "丽丽";
					r.sex = "女女女女女女";
				}
			}
			x = (x + 1) % 2;
		}
	}
}

// 输出

class Output implements Runnable {
	Resource r;

	Output(Resource r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			synchronized(r) {
				System.out.println(r.name + "...." + r.sex);
			}
		}
	}
}

public class ResourceDemo {

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
