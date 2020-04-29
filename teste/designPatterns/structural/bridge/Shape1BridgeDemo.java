package structural.bridge;

import structural.bridge.shape1.BlueCircle;
import structural.bridge.shape1.Circle;
import structural.bridge.shape1.RedSquare;
import structural.bridge.shape1.Square;

public class Shape1BridgeDemo {

	public static void main(String args[]) {
		Circle circle = new BlueCircle();

		Square square = new RedSquare();

		circle.applyColor();
		square.applyColor();

		/*
		 * With the way the code is in shape 1 if I wanted a new color, I would have to
		 * create all classes related again blueTriangulo, redTriangule etc. On shape
		 * one the color is connected with the shape.
		 */
	}

}
