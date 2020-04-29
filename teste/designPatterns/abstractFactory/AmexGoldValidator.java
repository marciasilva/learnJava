package abstractFactory;

import abstractFactory.model.CreditCard;

public class AmexGoldValidator implements Validator{
	@Override
	public boolean isValid(CreditCard creditCard) {
		return false;
	}
}
