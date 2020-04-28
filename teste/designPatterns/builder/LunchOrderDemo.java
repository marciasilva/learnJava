package builder;

public class LunchOrderDemo {

	public static void main(String[] args) {
		LunchOrder.Builder lob = new LunchOrder.Builder();			
		
		lob.bread("french").condiments("salat").dressing("mustard").meat("veggieBurguer");
		
		LunchOrder lo = lob.build();
		
		System.out.println(lo.getDressing());
	}

}
