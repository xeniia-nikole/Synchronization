package task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private Showroom showroom;

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Customer(Showroom showroom) {
        this.showroom = showroom;
    }

    public Car buyCar() {
        String nameCustomer = Thread.currentThread().getName();
        Car car = null;
            try {
                lock.lock();
                System.out.printf("%s зашел в автосалон\n", nameCustomer);
                Thread.sleep(2000);
                while (showroom.cars().size() == 0) {
                    System.out.println("Нет автомобилей в наличии");
                    condition.await();
                }
                car = showroom.cars().remove(0);
                System.out.printf("%s уехал домой на новом автомобиле %s\n",
                        nameCustomer, car.getCarName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        return car;
    }

    public void recieveCar() {
        try {
            lock.lock();
            Thread.sleep(1000);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
