package reflection;

import multiAndConcurrency.BankAccount;

@WorkHandler(useThreadPool = false)
public class UpdatedAccWorker implements Runnable, TaskWorker {

	private BankAccount ba;

	public BankAccount getBa() {
		return ba;
	}

	public void setBa(BankAccount ba) {
		this.ba = ba;
	}

	public void setTarget(Object target) {
		if (BankAccount.class.isInstance(target)) {
			setBa((BankAccount) target);
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void doWork() {
		Thread t = new Thread(HighVolumeAccount.class.isInstance(ba) ? (HighVolumeAccount) ba : this);
		t.start();
	}

	@Override
	public void run() {
		char txType = 'd';
		int amt = 10;

		if (txType == 'w') {
			ba.withdrawal(amt);
		} else {
			ba.deposit(amt);
		}
		// TODO Auto-generated method stub

	}

}
