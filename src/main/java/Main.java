public class Main {
    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter();

        Thread caller = new Thread(null, callCenter::addCall);
        Thread operator1 = new Thread(null, callCenter::takeToWork, "Оператор 1");
        Thread operator2 = new Thread(null, callCenter::takeToWork, "Оператор 2");
        Thread operator3 = new Thread(null, callCenter::takeToWork, "Оператор 3");

        caller.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        operator1.start();
        operator2.start();
        operator3.start();

    }
}
