package abstractFactory;

import abstractFactory.model.AmexGoldCC;
import abstractFactory.model.AmexPlatinumCC;
import abstractFactory.model.CardType;
import abstractFactory.model.CreditCard;

public class AmexFactory extends CreditCardFactory {

	@Override
	public CreditCard getCreditCard(CardType cardType) {
		switch (cardType) {
		case GOLD:
			return new AmexGoldCC();
		case PLATINUM:
			return new AmexPlatinumCC();
		default:
			break;
		}
		return null;
	}

	@Override
	public Validator getValidator(CardType cardType) {
		switch (cardType) {
		case GOLD:
			return new AmexGoldValidator();
		case PLATINUM:
			return new AmexPlatinumValidator();
		default:
			break;
		}
		return null;
	}
}
