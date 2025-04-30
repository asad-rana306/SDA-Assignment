package Shapes;

public class ColorDecorator extends ShapeDecorator {
    private double decorationPrice = 5.0;

    public ColorDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        System.out.println("Adding Color decoration");
        System.out.println("Decoration Price: " + decorationPrice);
        System.out.println("Total Cost: " + computeCost());
    }
}
