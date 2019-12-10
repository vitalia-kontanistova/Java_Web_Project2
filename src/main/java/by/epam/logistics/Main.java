package by.epam.logistics;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {

        Base base = Base.getInstance();

        Van van1 = new Van(base, "Van1", new AtomicBoolean(false));
        Van van2 = new Van(base, "Van2", new AtomicBoolean(true));
        Van van3 = new Van(base, "Van3", new AtomicBoolean(false));
        Van van4 = new Van(base, "Van4", new AtomicBoolean(false));
        Van van5 = new Van(base, "Van5", new AtomicBoolean(true));
        Van van6 = new Van(base, "Van6", new AtomicBoolean(false));
        Van van7 = new Van(base, "Van7", new AtomicBoolean(false));
        Van van8 = new Van(base, "Van8", new AtomicBoolean(true));
        Van van9 = new Van(base, "Van9", new AtomicBoolean(false));
        Van van10 = new Van(base, "Van10", new AtomicBoolean(false));


        base.processQueue();

    }

}

