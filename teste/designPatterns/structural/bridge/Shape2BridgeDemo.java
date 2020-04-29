package structural.bridge;

import structural.bridge.shape2.Blue;
import structural.bridge.shape2.Circle;
import structural.bridge.shape2.Color;
import structural.bridge.shape2.Red;
import structural.bridge.shape2.Shape;
import structural.bridge.shape2.Square;

public class Shape2BridgeDemo {

	public static void main(String[] args) {
		Color blue = new Blue();

		Shape square = new Square(blue);
		
		Color red = new Red();
		Shape circle = new Circle(red);

		circle.applyColor();
		square.applyColor();
		
		/*On this model if we want a new color, we just need to create the color*/

	}

}
