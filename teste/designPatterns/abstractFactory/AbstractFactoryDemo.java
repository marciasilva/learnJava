package abstractFactory;

import abstractFactory.model.CardType;
import abstractFactory.model.CreditCard;

public class AbstractFactoryDemo {

	public static void main(String[] args) {
		
		CreditCardFactory abstractCCF = CreditCardFactory.getCreditCardFactory(890);
		CreditCard card = abstractCCF.getCreditCard(CardType.PLATINUM);
		System.out.println(card.getClass());
		
		abstractCCF = CreditCardFactory.getCreditCardFactory(630);
		CreditCard card1 = abstractCCF.getCreditCard(CardType.GOLD);
		System.out.println(card1.getClass());

	}

}
