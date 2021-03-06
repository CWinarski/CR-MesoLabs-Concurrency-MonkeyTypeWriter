package io.zipcoder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {
    Lock sharedLock = new ReentrantLock();
    public SafeCopier(String toCopy){
        super(toCopy);
    }

    public void run() {
        boolean keepGoing = true;
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        while(keepGoing){
            if(sharedLock.tryLock()){
                if (stringIterator.hasNext()){
                    copied += stringIterator.next() + " " + Thread.currentThread().getName() + " ";
                } else keepGoing = false;
                sharedLock.unlock();
            }

        }


    }
}
