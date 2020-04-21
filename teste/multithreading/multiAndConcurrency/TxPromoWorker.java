package multiAndConcurrency;

public class TxPromoWorker extends TxWorker {

	public TxPromoWorker(BankAccount account, char txType, int amt) {
		super(account, txType, amt);
	}

	public void run() {
		if (txType == 'w') {
			account.withdrawal(amt);
		} else if (txType == 'd') {
			synchronized (account) {
				account.deposit(amt);
				if (account.getBalance() > 500) {
					int bonus = (int) ((account.getBalance() - 500) * 0.1);
					account.deposit(bonus);
				}
			}
		}
	}

}
