package structural.flyweight;

public class FlywigthtInventoryDem {

	public static void main(String[] args) {
		InventorySystem ims = new InventorySystem();

		ims.takeOrder("Roomba", 221);
		ims.takeOrder("Samsung TV", 432);
		ims.takeOrder("Samsung TV", 876);
		ims.takeOrder("Bose Headphones", 876);
		ims.takeOrder("Roomba", 653);

		ims.process();

		System.out.println(ims.report());
		// it will be always 3 items
		// ans we take a order in inv system we check if the item already exists, so it
		// doesn't create another one and return the instance

	}

}
