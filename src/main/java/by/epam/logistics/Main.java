package by.epam.logistics;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Terminal> terminals = new ArrayList<>();
        terminals.add(new Terminal(1));
        terminals.add(new Terminal(2));
        terminals.add(new Terminal(3));
        terminals.add(new Terminal(4));
        terminals.add(new Terminal(5));

        Base base = new Base(terminals);

        Van van1 = new Van("Van1", new AtomicBoolean(false), base);
        Van van2 = new Van("Van2", new AtomicBoolean(true), base);
        Van van3 = new Van("Van3", new AtomicBoolean(false), base);
        Van van4 = new Van("Van4", new AtomicBoolean(false), base);
        Van van5 = new Van("Van5", new AtomicBoolean(true), base);
        Van van6 = new Van("Van6", new AtomicBoolean(false), base);
        Van van7 = new Van("Van7", new AtomicBoolean(false), base);
        Van van8 = new Van("Van8", new AtomicBoolean(true), base);
        Van van9 = new Van("Van9", new AtomicBoolean(false), base);
        Van van10 = new Van("Van10", new AtomicBoolean(false), base);

//        Thread.sleep(5000);
//        Deque<Van> vans = base.getVans();
//
//        System.out.println();

//        new Thread(base).start();
    }
}