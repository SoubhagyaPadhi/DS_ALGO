package com.skp.demo;

class Singleton {

	/**
	 * The JVM, or sometimes the compiler, can optimize the code by reordering or
	 * caching the value of variables (and not making the updates visible) 
	 * volatile
	 * keyword, which guarantees that any read/write operation of a variable shared
	 * by many threads would be atomic and not cached
	 */
	private static volatile Singleton instance = null;

	private Singleton() {

	}

	public static synchronized Singleton getInstance() {

		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

}

public class SingletonTestMain {

	public static void main(String[] args) {

		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();

		System.out.println(s1.hashCode() + "--" + s2.hashCode());

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Singleton s3 = Singleton.getInstance();
				System.out.println("from t1-" + s3.hashCode());

			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				Singleton s4 = Singleton.getInstance();
				System.out.println("from t2-" + s4.hashCode());

			}
		});

		t1.start();
		t2.start();
	}

}
