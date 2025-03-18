class Engine1 {
    public void start() {
        System.out.println("Engine is starting...");
    }
}

class Car1 {
    private Engine1 engine1;

    public Car1(Engine1 engine1) {
        this.engine1 = engine1;
    }

    public void drive() {
        engine1.start();
        System.out.println("Car is driving...");
    }
}

public class LowCoupling {
    public static void main(String[] args) {
        Engine1 engine1 = new Engine1();
        Car1 car1 = new Car1(engine1);
        car1.drive();
    }
}

