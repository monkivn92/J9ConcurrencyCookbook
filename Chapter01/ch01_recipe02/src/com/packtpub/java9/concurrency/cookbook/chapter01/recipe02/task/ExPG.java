package com.packtpub.java9.concurrency.cookbook.chapter01.recipe02.task;

public class ExPG extends Thread
{
    long current = 1L;
    @Override
    public void run()
    {
        while(true)
        {
            if(isPrime(current))
            {
                System.out.printf("Number %d is Prime\n", current);
            }

            if(isInterrupted())
            {
                System.out.printf("The Prime Generator has been Interrupted\n");
                return;
            }

            current++;
        }

    }

    private boolean isPrime(long number)
    {
        if (number <= 2)
        {
            return true;
        }
        for (long i = 2; i < number; i++)
        {
            if ((number % i) == 0)
            {
                return false;
            }
        }
        return true;
    }

}
