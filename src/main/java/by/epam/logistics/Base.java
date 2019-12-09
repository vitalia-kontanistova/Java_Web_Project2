package by.epam.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base implements Runnable {
    private Lock lock;

//    private Condition condition;
    private List<Terminal> terminals;
    private Queue<Van> vans;

    public Base(List<Terminal> terminals) {
        this.terminals =  terminals;

        lock = new ReentrantLock();
    }



    public Terminal findFreeTerminal(Van van) {
        lock = van.getLock();
//        condition = van.getCondition();

        if (van.isPerishable()) {
            for (Terminal terminal : terminals) {
                while (true) {


                    if (terminal.isFree()) {
                        lock.lock();//
                        terminal.setFree(false);
//                        condition.signal();//
                        lock.unlock();//
                        return terminal;
                    }
                }
            }
        } else {
            for (Terminal terminal : terminals) {

                if (terminal.isFree()) {
                    lock.lock();
                    terminal.setFree(false);
//                    condition.signal();//
                    lock.unlock();
                    return terminal;
                }
            }
        }
        return new Terminal(-1);
    }

    @Override
    public void run() {

    }
}