package com.packtpub.java9.concurrency.cookbook.chapter01.recipe04.main;

import java.util.concurrent.TimeUnit;

import com.packtpub.java7.concurrency.cookbook.chapter01.recipe04.task.ConsoleClock;

/**
 * Main class of the Example. Creates a FileClock runnable object and a Thread
 * to run it. Starts the Thread, waits five seconds and interrupts it.
 *
 */
/*
why interrupt() not work as expected and how does it work???
The thread is still running simply because you catch InterruptedException
and keep running. interrupt() primarily sets a flag in the Thread object,
which you can check with isInterrupted().
It also causes some methods -- sleep(), join Object.wait(),
in particular -- to return immediately by throwing an InterruptedException.
It also causes some I/O operations to immediately terminate.
If you're seeing the printouts from your catch block,
then you can see that interrupt() is working.

As others have said, you catch the interrupt,
but do nothing with it.
What you need to do is propagate the interrupt using logic such as,

while(!Thread.currentThread().isInterrupted()){
    try{
        // do stuff
    }catch(InterruptedException e){
        Thread.currentThread().interrupt(); // propagate interrupt
    }
}
Using looping logic, such as while(true) is just lazy coding.
Instead, poll the thread's interrupted flag in order
to determine termination via interruption.

*/
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates a FileClock runnable object and a Thread
		// to run it
		ConsoleClock clock = new ConsoleClock();
		Thread thread = new Thread(clock);

		// Starts the Thread
		thread.start();
		try {
			// Waits five seconds
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Interrupts the Thread
		thread.interrupt();
	}
}
