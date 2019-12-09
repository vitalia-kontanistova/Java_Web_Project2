package by.epam.logistics;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Van van1 = new Van("Van1", new AtomicBoolean(false));
        Van van2 = new Van("Van2", new AtomicBoolean(true));
        Van van3 = new Van("Van3", new AtomicBoolean(false));
        Van van4 = new Van("Van4", new AtomicBoolean(false));
        Van van5 = new Van("Van5", new AtomicBoolean(true));
        Van van6 = new Van("Van6", new AtomicBoolean(false));
        Van van7 = new Van("Van7", new AtomicBoolean(false));
        Van van8 = new Van("Van8", new AtomicBoolean(true));
        Van van9 = new Van("Van9", new AtomicBoolean(false));
        Van van10 = new Van("Van10", new AtomicBoolean(false));

    }
}