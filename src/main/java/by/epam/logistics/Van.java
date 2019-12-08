package by.epam.logistics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Van implements Runnable {
    private Base base;
    private String name;
    private boolean perishable;

    private Thread threadVan;
    private static Lock lock;

    public Van(String name, boolean perishable) {
        this.name = name;
        this.perishable = perishable;
        lock = new ReentrantLock();
        threadVan = new Thread(this);
    }

    public Van(String name, boolean perishable, Base base) {
        this(name, perishable);
        this.base = base;
    }


    public String getName() {
        return name;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Thread getThreadVan() {
        return threadVan;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is arriving to the base.");

            lock.lock();
            Terminal currentTerminal = base.findFreeTerminal(this);

            if (currentTerminal.getId() > 0) {
                currentTerminal.process(this);
                lock.unlock();
            } else {
                TimeUnit.MILLISECONDS.sleep(10);
                nextTry();
//                return;
            }
            System.out.println(name + " release base.");
        } catch (NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void nextTry() {
        run();
    }
}
