package by.epam.logistics;


public class Main {

    public static void main(String[] args) {
        Base base = new Base(new Terminal(1), new Terminal(2), new Terminal(3), new Terminal(4), new Terminal(5));

        Van van1 = new Van("Van1", false, base);
        Van van2 = new Van("Van2", true, base);
        Van van3 = new Van("Van3", false, base);
        Van van4 = new Van("Van4", false, base);
        Van van5 = new Van("Van5", true, base);
        Van van6 = new Van("Van6", false, base);
        Van van7 = new Van("Van7", false, base);
        Van van8 = new Van("Van8", true, base);
        Van van9 = new Van("Van9", false, base);
        Van van10 = new Van("Van10", false, base);

        van1.getThreadVan().
                start();
        van2.getThreadVan().
                start();
        van3.getThreadVan().
                start();
        van4.getThreadVan().
                start();
        van5.getThreadVan().
                start();
        van6.getThreadVan().
                start();
        van7.getThreadVan().
                start();
        van8.getThreadVan().
                start();
        van9.getThreadVan().
                start();
        van10.getThreadVan().
                start();
    }
}