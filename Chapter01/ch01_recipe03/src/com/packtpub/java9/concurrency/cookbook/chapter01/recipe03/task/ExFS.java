package com.packtpub.java9.concurrency.cookbook.chapter01.recipe03.task;

import java.io.File;

public class ExFS implements Runnable
{
    private String fpath;
    private String target;

    public ExFS(String p, String t)
    {
        this.fpath = p;
        this.target  = t;
    }

    @Override
    public void run()
    {
        File dir = new File(fpath);
        if(dir.isDirectory())
        {
            try
            {
                processDir(dir);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                cleanResources();
            }
        }

    }

    /**
     * Method for cleaning the resources. In this case, is empty
     */
    private void cleanResources(){}

    private void processDir(File dir) throws InterruptedException
    {
        // Get the content of the directory
        File list[] = dir.listFiles();
        if (list != null)
        {
            for (int i = 0; i < list.length; i++)
            {
                if (list[i].isDirectory())
                {
                    // If is a directory, process it
                    processDir(list[i]);
                }
                else
                {
                    // If is a file, process it
                    processFile(list[i]);
                }
            }
        }

        if(Thread.interrupted())
        {
            throw new InterruptedException();
        }
    }

    private void processFile(File file) throws InterruptedException
    {
        // Check the name
        if (file.getName().equals(this.target))
        {
            System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        // Check the interruption
        if (Thread.interrupted())
        {
            throw new InterruptedException();
        }
    }



}
