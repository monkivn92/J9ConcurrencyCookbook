package com.packtpub.java9.concurrency.cookbook.chapter01.recipe02.main;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe02.task.ExPG;
public class ExMain
{
    public static void main(String... args)
    {
        Thread task = new ExPG();
        task.start();
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        task.interrupt();

        // Write information about the status of the Thread
        System.out.printf("Main: Status of the Thread: %s\n", task.getState());
        System.out.printf("Main: isInterrupted: %s\n", task.isInterrupted());
        System.out.printf("Main: isAlive: %s\n", task.isAlive());

    }
}
