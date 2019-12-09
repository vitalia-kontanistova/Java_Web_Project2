package by.epam.logistics;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Van implements Runnable {
    private Base base;
    private String name;
    private AtomicBoolean perishable;

    private Thread threadVan;
    private static Lock lock;

    public Van(String name, AtomicBoolean perishable, Base base) {
        this.name = name;
        this.perishable = perishable;
        this.base = base;
        threadVan = new Thread(this);
        lock = new ReentrantLock();
        threadVan.start();
    }

    public String getName() {
        return name;
    }

    public AtomicBoolean isPerishable() {
        return perishable;
    }

    @Override
    public void run() {
        try {

            Random random = new Random();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
            System.out.println(name + " is arriving to the base.");

            lock.lock();
            base.addVan(this);
            lock.unlock();

        } catch (NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}