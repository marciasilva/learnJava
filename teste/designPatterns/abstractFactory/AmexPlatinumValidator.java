package abstractFactory;

import abstractFactory.model.CreditCard;

public class AmexPlatinumValidator implements Validator{
	
	@Override
	public boolean isValid(CreditCard creditCard) {
		return false;
	}
}
