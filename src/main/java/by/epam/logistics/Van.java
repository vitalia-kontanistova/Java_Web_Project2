package by.epam.logistics;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Van implements Runnable {
    private Semaphore terminals;
    private String name;
    private AtomicBoolean perishable;
    private Base base;
    private Thread vanThread;
    Lock lock;
    Condition condition;

    public Thread getVanThread() {
        return vanThread;
    }

    public String getName() {
        return name;
    }

    public AtomicBoolean getPerishable() {
        return perishable;
    }

    public Van(Base base, String name, AtomicBoolean perishable) {
        this.base = base;
        this.terminals = base.getTerminals();
        this.name = name;
        this.perishable = perishable;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public Lock getLock() {
        return lock;
    }


    public Condition getCondition() {
        return condition;
    }

    @Override
    public void run() {
        try {
            base.addVan(this);
            lock.lock();
            condition.await();
            lock.unlock();

            System.out.println(this.getName() + " arrive and waiting for terminal");
            terminals.acquire();

            base.processVan(this);

            System.out.println(this.getName() + " release base");
            terminals.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "" + name;
    }
}