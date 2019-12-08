package by.epam.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private Lock lock;
    private List<Terminal> base;

    public Base(Terminal t1, Terminal t2, Terminal t3, Terminal t4, Terminal t5) {
        base = new ArrayList<>();
        if (t1 != null)
            base.add(t1);
        if (t2 != null)
            base.add(t2);
        if (t3 != null)
            base.add(t3);
        if (t4 != null)
            base.add(t4);
        if (t5 != null)
            base.add(t5);

        lock = new ReentrantLock();
    }

    public Terminal findFreeTerminal(Van van) {

        if (van.isPerishable()) {
            lock.lock();
            for (Terminal terminal : base) {
                while (true) {

                    if (terminal.isFree()) {
                        terminal.setFree(false);
                        return terminal;
                    }
                }
            }
        } else {
            for (Terminal terminal : base) {
                if (terminal.isFree()) {
                    terminal.setFree(false);
                    return terminal;
                }
            }
            lock.unlock();
        }
        return new Terminal(-1);
    }
}