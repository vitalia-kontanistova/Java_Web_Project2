package by.epam.logistics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private int id;
    private boolean free;
    private Lock lock;


    public int getId() {
        return id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Terminal(int id) {
        this.id = id;
        this.free = true;
        lock = new ReentrantLock();
    }

    public void process(Van van) {

        System.out.println(van.getName() + " processing on terminal N" + this.getId() + "...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        this.setFree(true);
        lock.unlock();
    }
}