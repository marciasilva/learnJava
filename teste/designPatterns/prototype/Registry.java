package prototype;

import java.util.HashMap;
import java.util.Map;

import prototype.model.*;

public class Registry {
	
	private Map<String, Item> items = new HashMap<String, Item>();
	
	public Registry() {
		loadItems();
	}
	
	
	//Heart and soul of prototype
	public Item createItem(String type) {
		Item item = null;
		
		try {
			item = (Item)(items.get(type)).clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	private void loadItems() {
		Movie movie = new Movie();
		movie.setTitle("Zoo of Varsovia");
		movie.setPrice(24.99);
		movie.setRuntime("2.3 hours");
		items.put("Movie", movie);
		
		Book book = new Book();
		book.setNumberOfPages(355);
		book.setPrice(19.99);
		book.setTitle("The seven husbands of Helen Hugo");
		
		items.put("Book", book);
	}
}
