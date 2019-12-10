package by.epam.logistics;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Van implements Runnable {
    private Semaphore terminals;
    private String name;
    private AtomicBoolean perishable;
    private Base base;
    private Thread vanThread;

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
        base.addVan(this);
        this.vanThread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1500));
            System.out.println(this.getName() + " arrive and waiting for terminal");
            terminals.acquire();

            System.out.println(this.getName() + " processing...");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1500));

            System.out.println(this.getName() + " release base");
            terminals.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}