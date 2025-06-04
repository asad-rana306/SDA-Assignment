package Shapes;

public class Circle extends Shape {
    public Circle() {
        this.price = 20.0;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
        System.out.println("Price: " + getPrice());
    }
}
