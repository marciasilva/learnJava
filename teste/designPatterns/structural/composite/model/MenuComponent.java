/**
 * 
 */
package structural.composite.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author emianva
 *
 */
public abstract class MenuComponent {
	String name;
	String url;
	List<MenuComponent> menuComponents = new ArrayList<>();
	
	public MenuComponent add(MenuComponent mc) {
		throw new UnsupportedOperationException("Feature not implement at this level");
	}
	
	public MenuComponent remove(MenuComponent mc) {
		throw new UnsupportedOperationException("Feature not implement at this level");
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public abstract String toString();
	
	String print(MenuComponent menuComponent) {
		StringBuilder builder = new StringBuilder(name);
		builder.append(": ");
		builder.append(url);
		builder.append("\n");
		return builder.toString();
	}
}
