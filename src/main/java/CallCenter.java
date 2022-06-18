import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    private static final int ITERATIONS = 60;
    private final ConcurrentLinkedQueue<Integer> storage = new ConcurrentLinkedQueue<>();
    //Была использована ConcurrentLinkedQueue, т.к она быстро работает, исключает блокировки и работает по принципу
    //FIFO, отдавая номера телефонов по очереди их поступления.
    private Random random = new Random();


    public void addCall() {
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                Thread.sleep(500);
                storage.offer(Math.abs(random.nextInt() * random.nextInt()) % 10000000 + 920000000);
                System.out.println("Новый звонок ожидает ответа");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void takeToWork() {
        while (!storage.isEmpty()) {
            try {
                Integer check = storage.poll();
                if (check != null) {
                    Thread.sleep(3000);
                    System.out.println("Оператор " + Thread.currentThread().getName()
                            + " взят в работу номер " + check);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}

