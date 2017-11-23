package com.packtpub.java9.concurrency.cookbook.chapter01.recipe09.group;

/**
 * Class that extends the ThreadGroup class to implement a uncaught exceptions
 * method
 *
 */
public class MyThreadGroup extends ThreadGroup {

	/**
	 * Constructor of the class. Calls the parent class constructor
	 * 
	 The Android NDK toolset is a great example of full C/C++ support that was added originally for game development teams to enable them to get the best possible performance out of the device by avoiding Java and the Android Java runtime Dalvik, the virtual machine on which Android Java code is executed on. It has been regularly improved to enable every Android service.* @param name
	 */
	public MyThreadGroup(String name) {
		super(name);
	}

	/**
	 * Method for process the uncaught exceptions
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// Prints the name of the Thread
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		// Print the stack trace of the exception
		e.printStackTrace(System.out);
		// Interrupt the rest of the threads of the thread group
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}
