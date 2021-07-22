package task2;

import java.util.ArrayList;
import java.util.List;

public class Showroom {
    static int maxQuantity = 10;

    List<Car> cars = new ArrayList<>(maxQuantity);
    Customer customer = new Customer(this);

    public List<Car> cars(){
        return cars;
    }

    public void sellCar(){
        System.out.printf("%s открыл продажи автомобилей!\n", Thread.currentThread().getName());
        int i = 0;
        try {
            while (maxQuantity != 0) {
                i++;
                Thread.sleep(5000);
                Car car = new Car("автомобиль #" + i);
                cars().add(car);
                System.out.printf("%s подготовил %s к продаже\n",
                        Thread.currentThread().getName(), car.getCarName());
                customer.recieveCar();
                maxQuantity--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newVisitor(){
        customer.buyCar();
    }

}
