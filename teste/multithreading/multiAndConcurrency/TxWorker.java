package multiAndConcurrency;

public class TxWorker implements Runnable {

	protected BankAccount account;
	protected char txType;
	protected int amt;	

	public TxWorker(BankAccount account, char txType, int amt) {
		super();
		this.account = account;
		this.txType = txType;
		this.amt = amt;
	}

	@Override
	public void run() {
		if (txType == 'w') {
			account.withdrawal(amt);
		} else if (txType == 'd') {
			account.deposit(amt);
		}
	}

}
