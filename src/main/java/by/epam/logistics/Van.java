package by.epam.logistics;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Van implements Runnable {
    private Base base;
    private String name;
    private boolean perishable;

    private Thread threadVan;
    private static Lock lock;
//    private Condition condition;

    public Van(String name, boolean perishable) {
        this.name = name;
        this.perishable = perishable;
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

    public Lock getLock() {
        return lock;
    }

//    public Condition getCondition() {
//        return condition;
//    }

    @Override
    public void run() {
        try {
            boolean success = false;
            System.out.println(name + " is arriving to the base.");

            Terminal currentTerminal = base.findFreeTerminal(this);
            lock.lock();

            if (currentTerminal.getId() > 0) {
                currentTerminal.process(this);
                success = true;
                lock.unlock();
                TimeUnit.SECONDS.sleep(1);

            } else {
                TimeUnit.MILLISECONDS.sleep(10);
//                condition.await();
                currentTerminal = base.findFreeTerminal(this);//
                if (currentTerminal.getId() > 0) {
                    currentTerminal.process(this);//
                    success = true;
                }
                lock.unlock();
//                run();
            }
            if (success) {
                System.out.println(name + " release base.");
            }
        } catch (NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
