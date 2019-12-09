package by.epam.logistics;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base implements Runnable {
    private Lock lock;

    private Thread baseThread;
    private List<Terminal> terminals;
    private Deque<Van> vans;


    public Base(List<Terminal> terminals) {
        this.terminals = terminals;

        lock = new ReentrantLock();
        vans = new LinkedList<>();
//        baseThread = new Thread(this);
//        baseThread.run();//////////////////////////
    }


    public void addVan(Van van) {
        if (van.isPerishable().get()) {
            lock.lock();
            vans.addFirst(van);
            lock.unlock();

        } else {
            lock.lock();
            vans.addLast(van);
            lock.unlock();
        }
        new Thread(this).start();
    }


    public Deque<Van> getVans() {
        return vans;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (!vans.isEmpty()) {
                Van van = vans.pollFirst();
                for (Terminal terminal : terminals) {
                    if (terminal.isFree().get()) {
                        terminal.process(van);
                        System.out.println(van.getName() + " release the base.");
                        lock.unlock();
                        return;
                    }
                }
                lock.unlock();
                Thread.yield();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}