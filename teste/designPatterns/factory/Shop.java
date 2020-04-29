package factory;

import factory.model.CartPage;
import factory.model.ItemPage;
import factory.model.SearchPage;
import factory.model.Website;

public class Shop extends Website {

	@Override
	public void createWebsite() {
		pages.add(new CartPage());
		pages.add(new ItemPage());
		pages.add(new SearchPage());
		
		// TODO Auto-generated method stub
		
	}

}
