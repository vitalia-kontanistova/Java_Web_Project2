package by.epam.logistics;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCheck {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int account = 0;

    public static void main(String[] args) {
        new Thread(new Minus()).start();
        new Thread(new Pluss()).start();

    }

    static class Pluss implements Runnable {

        @Override
        public void run() {
            lock.lock();
            account += 0;
            condition.signal();
            lock.unlock();
        }
    }

    static class Minus implements Runnable {

        @Override
        public void run() {
            if (account < 10) {
                try {
                    lock.lock();
                    System.out.println("account=" + account);
                    condition.await();
                    System.out.println("account=" + account);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("account=" + account);
            account -= 10;
            System.out.println("account=" + account);

        }
    }
}