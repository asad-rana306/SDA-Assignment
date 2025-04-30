package Shapes;

public class Square extends Shape {
    public Square() {
        this.price = 30.0;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square");
        System.out.println("Price: " + getPrice());
    }
}
