package com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.task;

public class ExTask implements Runnable
{


    @Override
    public void run()
    {
        @SuppressWarnings("unused")
        long current = 1L;
        @SuppressWarnings("unused")
        long max = 1000L;
        @SuppressWarnings("unused")
        long numPrimes = 0L;

        System.out.printf("Thread %s START \n", Thread.currentThread().getName());

        while (current <= max)
        {
            if(isPrime(current))
            {
                numPrimes++;
            }
            current++;
        }

        System.out.printf("Thread '%s': END. Number of Primes: %d\n", Thread.currentThread().getName(), numPrimes);

    }

    public boolean isPrime(long num)
    {
        if(num <= 2)
        {
            return true;
        }
        for(long i = 2; i<Math.sqrt(num); i++)
        {
            if(num%i == 0)
            {
                return false;
            }
        }
        return true;
    }


}
