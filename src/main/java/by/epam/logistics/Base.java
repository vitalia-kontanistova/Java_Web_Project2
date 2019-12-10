package by.epam.logistics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base{
    private Semaphore terminals;
    private Deque<Van> vans;
    private Lock locker;

    private static Base instance;

    private Base() {
        this.terminals = new Semaphore(5);
        locker = new ReentrantLock();
        vans = new ArrayDeque<>();
    }

    public static Base getInstance() {
        if (instance == null) {
            synchronized (Base.class) {
                if (instance == null) {
                    instance = new Base();
                }
            }
        }
        return instance;
    }

    public Semaphore getTerminals() {
        return terminals;
    }

    public void addVan(Van van) {
        locker.lock();
        if (van.getPerishable().get()) {
            vans.addFirst(van);
        } else {
            vans.addLast(van);
        }
        locker.unlock();
    }

    public void processQueue() {
        while (!vans.isEmpty()) {
            locker.lock();
            Van van = vans.pollFirst();
            van.getVanThread().start();
            locker.unlock();
        }
    }

    public void processVan(Van van) {
        try {
            Random random = new Random();
            System.out.println(van.getName() + " processing...");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}