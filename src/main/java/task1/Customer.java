package task1;

public class Customer {
    private Showroom showroom;

    public Customer(Showroom showroom) {
        this.showroom = showroom;
    }

    public synchronized Car buyCar() {
        try {
            System.out.printf("%s зашел в автосалон\n", Thread.currentThread().getName());
            Thread.sleep(2000);
            while (showroom.cars().size() == 0) {
                System.out.println("Нет автомобилей в наличии");
                wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s уехал домой на новом автомобиле\n", Thread.currentThread().getName());
        return showroom.cars().remove(0);
    }

    public synchronized void recieveCar() {
        try {
            Thread.sleep(1000);
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
