package task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int customers = 10;

    public static void main(String[] args) {

        final Showroom showroom = new Showroom();

        List<Thread> threadsListCustomers = new ArrayList<>();

        new Thread(null, showroom::sellCar, "Автосалон").start();

        for (int i = 1; i < (customers + 1); i++) {

            try {
                Thread.sleep(3000);
                Thread customer = new Thread(null, showroom::newVisitor, "Покупатель #" + i);
                customer.start();
                threadsListCustomers.add(customer);
                for (Thread thread : threadsListCustomers) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Автомобили закончились и клиенты разъехались");
    }
}
