package by.epam.logistics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private Semaphore terminals;
    private Deque<Van> vans;
    private Lock lock;
    private Lock vanLock;
    private Condition vanCondtion;

    private static Base instance;

    private Base() {
        this.terminals = new Semaphore(5);
        lock = new ReentrantLock();
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
        lock.lock();
        if (van.getPerishable().get()) {
            vans.addFirst(van);
        } else {
            vans.addLast(van);
        }
        lock.unlock();
    }

    public void processQueue() {
        while (!vans.isEmpty()) {
            lock.lock();
            Van van = vans.pollFirst();
            vanLock = van.getLock();
            vanCondtion = van.getCondition();

            vanLock.lock();
            vanCondtion.signal();

            vanLock.unlock();
            lock.unlock();
        }
    }

    public void processVan(Van van) {
        try {
            Random random = new Random();
            System.out.println(van.getName() + " processing...");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}