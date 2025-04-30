import Shapes.Circle;
import Shapes.ColorDecorator;
import Shapes.Shape;
import Shapes.Square;
import filedecorators.FileDecorator;
import filedecorators.UTF8Decorator;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle();
        circle = new ColorDecorator(circle);
        circle.draw();
        Shape square = new Square();
        square = new ColorDecorator(square);
        square.draw();

        FileDecorator fileDecorator = new UTF8Decorator(null);
        try {
            File file = new File("Z:/lab3/decorator/src/assignment.txt");
            fileDecorator.decorate(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
