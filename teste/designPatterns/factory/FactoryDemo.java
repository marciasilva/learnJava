package factory;

import factory.model.Website;
import factory.model.WebsiteType;

public class FactoryDemo {

	public static void main(String[] args) {
		Website ws = WebsiteFactory.getWebsite(WebsiteType.BLOG);		
		System.out.println(ws.getPages());
		
		ws = WebsiteFactory.getWebsite(WebsiteType.SHOP);
		System.out.println(ws.getPages());
	}
}
