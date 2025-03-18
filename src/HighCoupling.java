class Car {
    private Engine engine = new Engine();
    public void drive() {
        engine.start();
        System.out.println("Car is driving...");
    }
}

class Engine {
    public void start() {
        System.out.println("Engine is starting...");
    }
}

public class HighCoupling {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}
