package reflection;

import multiAndConcurrency.BankAccount;

public class AccountWorker implements Runnable {

	private BankAccount ba;
	private HighVolumeAccount hva;

	public AccountWorker(HighVolumeAccount hva) {
		super();
		this.hva = hva;
	}

	public AccountWorker(BankAccount ba) {
		super();
		this.ba = ba;
	}	
	
	public void doWork() {
		Thread t = new Thread(hva != null ? hva : this);
		t.start();
	}

	@Override
	public void run() {
		// TODO read tx type and read tx amount
		char txType = 'd';
		int amt = 10;
		
		if(txType == 'w') {
			ba.withdrawal(amt);
		} else {
			ba.deposit(amt);
		}
	}
	
	

}
