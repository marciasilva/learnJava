package abstractFactory;

import abstractFactory.model.CreditCard;

public interface Validator {

	boolean isValid(CreditCard creditCard);

}
