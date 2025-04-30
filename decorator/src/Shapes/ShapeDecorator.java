package Shapes;

public abstract class ShapeDecorator extends Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public abstract void draw();

    public double computeCost() {
        return decoratedShape.getPrice() + price;
    }
}
