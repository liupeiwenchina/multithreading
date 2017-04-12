package com.multithreading;

//懒汉式

class Single {
	private static Single s = null;
	
	private Single() {}
	
	public static Single getInstance() {
		synchronized(Single.class) {
			if (s == null) s = new Single();  //存在线程安全隐患
			return s;
		}
	}
}


public class SingleDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


//second commit
//thrid commit