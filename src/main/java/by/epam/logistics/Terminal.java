package by.epam.logistics;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private int id;
    private AtomicBoolean free;
    private Lock lock;


    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void setFree(AtomicBoolean free) {
        this.free = free;
    }

    public Terminal(int id) {
        this.id = id;
        this.free = new AtomicBoolean(true);
        lock = new ReentrantLock();
    }

    public void process(Van van) {
        System.out.println(van.getName() + " processing on terminal N" + this.getId() + "...");
        try {
            Random random = new Random();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!isFree().get()) {
            lock.lock();
            this.setFree(new AtomicBoolean(true));
            lock.unlock();
        }
    }
}