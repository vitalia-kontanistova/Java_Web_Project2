package by.epam.logistics;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base implements Runnable {
    private Lock lock;
    private List<Terminal> terminals;
    private Deque<Van> vans;

    private volatile static Base instance;

    private Base() {
        terminals = new ArrayList<>();
        terminals.add(new Terminal(1));
        terminals.add(new Terminal(2));
        terminals.add(new Terminal(3));
        terminals.add(new Terminal(4));
        terminals.add(new Terminal(5));

        lock = new ReentrantLock();
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
        try {
            while (!vans.isEmpty()) {
                lock.lock();
                Van van = vans.pollFirst();

                findFreeTerminal().process(van);
                lock.unlock();
                System.out.println(van.getName() + " release the base.");
                Thread.yield();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public Terminal findFreeTerminal() {
        lock.lock();
        try {
            for (Terminal terminal : terminals) {
                if (terminal.isFree().get()) {
                    terminal.setFree(new AtomicBoolean(false));
                    lock.unlock();
                    return terminal;
                }
            }
        } catch (
                NullPointerException e) {
            e.printStackTrace();
        }
        return new Terminal(-1);
    }
}