package com.packtpub.java9.concurrency.cookbook.chapter01.recipe03.main;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe03.task.ExFS;

public class ExMain
{
    public static void main(String[] args)
    {
        ExFS fs = new ExFS("/media/DataBackup/java_practice/MasterConcurent", "Logger.java");
        Thread th = new Thread(fs);
        th.start();
        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        th.interrupt();

    }
}
