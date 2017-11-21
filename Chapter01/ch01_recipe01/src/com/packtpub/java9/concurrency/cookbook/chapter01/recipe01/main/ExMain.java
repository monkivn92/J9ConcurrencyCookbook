package com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.task.ExTask;
public class ExMain
{
    public static void main(String[] args)
    {

        Thread[] Th = new Thread[10];
        Thread.State[] status = new Thread.State[10];
        for(int i=0; i<10; i++)
        {
            Th[i] = new Thread(new ExTask(), String.valueOf(i));
            if(i%2 == 0)
            {
                Th[i].setPriority(Thread.MAX_PRIORITY);
            }
            else
            {
                Th[i].setPriority(Thread.MIN_PRIORITY);
            }
        }

        String logf = ExMain.class.getResource("../log/log.txt").getPath();
        System.out.println(logf);
        try(FileWriter file = new FileWriter(logf);
            PrintWriter pw = new PrintWriter(file))
        {
            for(int i=0; i<10; i++)
            {
                pw.println("Main: Status of thread "+i+ " : "+Th[i].getState());
                System.out.println("Main: Status of thread "+i+ " : "+Th[i].getState());
                status[i] = Th[i].getState();
            }
            for(int i=0; i<10; i++)
            {
                Th[i].start();
            }

            boolean finish=false;
            while (!finish)
            {
                for (int i = 0; i < 10; i++)
                {
                    if(status[i] != Th[i].getState())
                    {

                        pw.printf("Main : Id %d - %s\n", Th[i].getId(), Th[i].getName());
                        pw.printf("Main : Priority: %d\n", Th[i].getPriority());
                        pw.printf("Main : Old State: %s\n", status[i]);
                        pw.printf("Main : New State: %s\n", Th[i].getState());
                        pw.printf("Main : ************************************\n");
                        status[i] = Th[i].getState();

                    }

                }

                finish = true;
                for (int i = 0; i < 10; i++)
                {
                    finish &= Th[i].getState() == State.TERMINATED;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
