package com.skp.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Singleton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The JVM, or sometimes the compiler, can optimize the code by reordering or
	 * caching the value of variables (and not making the updates visible) volatile
	 * keyword, which guarantees that any read/write operation of a variable shared
	 * by many threads would be atomic and not cached
	 * 
	 * Make Singleton reflection proof:-
	 * 
	 * private Singleton(){
	 * 
	 * //Prevent form the reflection api. if (sSoleInstance != null){ throw new
	 * RuntimeException("Use getInstance() method to get the single instance of this
	 * class."); } }
	 *
	 * 
	 * Make Singleton thread safe:
	 * 
	 * Make Singleton safe from Serialization:
	 * 
	 * to provide the implementation of readResolve() method. readResolve() replaces
	 * the object read from the stream. This ensures that nobody can create another
	 * instance by serializing and deserializing the singleton.
	 * 
	 * 
	 * 
	 * //Make singleton from serialize and deserialize operation.
    protected SingletonClass readResolve() {
        return getInstance();
    }
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

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		/*
		 * Singleton s1 = Singleton.getInstance(); Singleton s2 =
		 * Singleton.getInstance();
		 * 
		 * System.out.println(s1.hashCode() + "--" + s2.hashCode());
		 * 
		 * Thread t1 = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { Singleton s3 = Singleton.getInstance();
		 * System.out.println("from t1-" + s3.hashCode());
		 * 
		 * } });
		 * 
		 * Thread t2 = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { Singleton s4 = Singleton.getInstance();
		 * System.out.println("from t2-" + s4.hashCode());
		 * 
		 * } });
		 * 
		 * t1.start(); t2.start();
		 */

		Singleton instance1 = Singleton.getInstance();
		ObjectOutput out = null;

		out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instance1);
		out.close();

		// deserialize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		Singleton instance2 = (Singleton) in.readObject();
		in.close();

		System.out.println("instance1 hashCode=" + instance1.hashCode());
		System.out.println("instance2 hashCode=" + instance2.hashCode());
	}

}
