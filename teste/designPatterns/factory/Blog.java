
package factory;

import factory.model.AboutPage;
import factory.model.CommentPage;
import factory.model.ContactPage;
import factory.model.PostPage;
import factory.model.Website;

public class Blog extends Website {

	@Override
	public void createWebsite() {
		pages.add(new PostPage());
		pages.add(new AboutPage());
		pages.add(new CommentPage());
		pages.add(new ContactPage());
		// TODO Auto-generated method stub

	}

}
