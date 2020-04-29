package structural.composite.model;

import java.util.Iterator;

public class Menu extends MenuComponent {

	public Menu(String name, String url) {
		this.name = name;
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); //builder pattern
		// Print from menuComponent
		builder.append(print(this));

		// Component pattern knows about the children call print from the children

		Iterator<MenuComponent> itr = menuComponents.iterator();
		while (itr.hasNext()) {
			MenuComponent mc = itr.next();
			builder.append(mc.toString());
		}
		return builder.toString();
	}
	
	@Override
	public MenuComponent add(MenuComponent mc) {
		menuComponents.add(mc);
		return mc;
	}

	@Override
	public MenuComponent remove(MenuComponent mc) {
		menuComponents.remove(mc);
		return mc;
	}

}
