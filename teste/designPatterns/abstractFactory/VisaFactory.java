package abstractFactory;

import abstractFactory.model.CardType;
import abstractFactory.model.CreditCard;
import abstractFactory.model.VisaBlackCC;
import abstractFactory.model.VisaGoldCC;

public class VisaFactory extends CreditCardFactory{
	
	@Override
	public CreditCard getCreditCard(CardType cardType) {
		switch (cardType) {
			case GOLD :
				return new VisaGoldCC();
			case PLATINUM :
				return new VisaBlackCC();
		}
		return null;
	}
	
	@Override
	public Validator getValidator (CardType cardType) {
		return new VisaValidator();
	}

}
