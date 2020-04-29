package structural.proxy;

public class TwitterDemo {

	public static void main(String[] args) {
		//TwitterService service = (TwitterService)SecurityProxy.newInstance(new TwitterServiceStub());
		
		TwitterService service = (TwitterService)SecurityProxy.newInstance(new TwitterServiceImpl());
		
		System.out.println(service.getTimeline("corona"));

	}

}
