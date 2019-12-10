package by.epam.logistics;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private Semaphore terminals;
    private Deque<Van> vans;
    private Lock locker;

    private static Base instance;

    private Base() {
        this.terminals = new Semaphore(5);
        locker = new ReentrantLock();
        vans = new LinkedList<>();
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
        if (van.getPerishable().get()) {
            locker.lock();
            vans.addFirst(van);
            locker.unlock();
        } else {
            locker.lock();
            vans.addLast(van);
            locker.unlock();
        }
    }

    public void processQueue() {
        while (!vans.isEmpty()) {
            locker.lock();
            Van van = vans.pollFirst();
            van.getVanThread().start();
            locker.unlock();
        }
    }
}